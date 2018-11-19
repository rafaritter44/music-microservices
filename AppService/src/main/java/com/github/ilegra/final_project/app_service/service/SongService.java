package com.github.ilegra.final_project.app_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ilegra.final_project.app_service.command.Config;
import com.github.ilegra.final_project.app_service.command.DetailedPlaylistCommand;
import com.github.ilegra.final_project.app_service.command.SongDetailCommand;
import com.github.ilegra.final_project.app_service.eureka.Discovery;
import com.github.ilegra.final_project.app_service.exception.NotFoundException;
import com.github.ilegra.final_project.app_service.feign.DetailedPlaylistImpl;
import com.github.ilegra.final_project.app_service.feign.SongImpl;
import com.github.ilegra.final_project.app_service.ribbon.LoadBalancer;
import com.netflix.hystrix.HystrixCommand.Setter;

@Service
public class SongService {
	
	@Autowired
	private Discovery discovery;
	@Autowired
	private Config config;
	private LoadBalancer loadBalancer;
	private final String PLAYLIST_SERVICE = "playlist-service";
	private final String SONG_SERVICE = "song-service";
	
	public List<SongImpl> detailPlaylist(String playlistID) throws NotFoundException {
		setLoadBalancer(PLAYLIST_SERVICE);
		DetailedPlaylistCommand playlistCommand = new DetailedPlaylistCommand(
				config.getCommandConfig(PLAYLIST_SERVICE), loadBalancer.getURL(), playlistID);
		Optional<DetailedPlaylistImpl> playlist = playlistCommand.execute();
		if(!playlist.isPresent())
			throw new NotFoundException("Playlist not found");
		setLoadBalancer(SONG_SERVICE);
		Setter setter = config.getCommandConfig(SONG_SERVICE);
		List<SongImpl> songs = new ArrayList<>();
		for(String id: playlist.get().getSongIdList()) {
			SongDetailCommand songCommand = new SongDetailCommand(setter, loadBalancer.getURL(), id);
			Optional<SongImpl> song = songCommand.execute();
			if(song.isPresent())
				songs.add(song.get());
			else {
				songCommand = new SongDetailCommand(setter, loadBalancer.getURL(), id);
				songCommand.execute().ifPresent(songs::add);
			}
		}
		return songs;
	}
	
	private void setLoadBalancer(String service) {
		loadBalancer = new LoadBalancer(discovery.getIPsAndPorts(service));
	}
	
}

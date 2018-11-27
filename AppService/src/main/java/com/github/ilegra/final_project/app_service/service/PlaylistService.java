package com.github.ilegra.final_project.app_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ilegra.final_project.app_service.command.AddPlaylistCommand;
import com.github.ilegra.final_project.app_service.command.Config;
import com.github.ilegra.final_project.app_service.command.PlaylistsCommand;
import com.github.ilegra.final_project.app_service.command.RemovePlaylistCommand;
import com.github.ilegra.final_project.app_service.command.UpdatePlaylistCommand;
import com.github.ilegra.final_project.app_service.eureka.Discovery;
import com.github.ilegra.final_project.app_service.feign.DetailedPlaylistWithName;
import com.github.ilegra.final_project.app_service.feign.Playlist;
import com.github.ilegra.final_project.app_service.ribbon.LoadBalancer;

@Service
public class PlaylistService {
	
	@Autowired
	private Discovery discovery;
	@Autowired
	private Config config;
	private LoadBalancer loadBalancer;
	private final String SERVICE = "playlist-service";
	
	public List<Playlist> listPlaylists(String userID) {
		setLoadBalancer();
		return new PlaylistsCommand(config.getCommandConfig(SERVICE), loadBalancer.getURL(), userID).execute();
	}
	
	public String addPlaylist(DetailedPlaylistWithName playlist) {
		setLoadBalancer();
		return new AddPlaylistCommand(config.getCommandConfig(SERVICE), loadBalancer.getURL(), playlist).execute();
	}
	
	public boolean removePlaylist(String id) {
		setLoadBalancer();
		return new RemovePlaylistCommand(config.getCommandConfig(SERVICE), loadBalancer.getURL(), id).execute();
	}
	
	public boolean updatePlaylist(String id, DetailedPlaylistWithName playlist) {
		setLoadBalancer();
		return new UpdatePlaylistCommand(config.getCommandConfig(SERVICE), loadBalancer.getURL(), id, playlist).execute();
	}
	
	private void setLoadBalancer() {
		loadBalancer = new LoadBalancer(discovery.getIPsAndPorts(SERVICE));
	}
	
}

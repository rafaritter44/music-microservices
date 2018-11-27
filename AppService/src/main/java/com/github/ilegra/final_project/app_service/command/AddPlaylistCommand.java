package com.github.ilegra.final_project.app_service.command;

import com.github.ilegra.final_project.app_service.feign.DetailedPlaylist;
import com.github.ilegra.final_project.app_service.feign.DetailedPlaylistWithName;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonEncoder;

public class AddPlaylistCommand extends HystrixCommand<String> {

	private String url;
	private DetailedPlaylistWithName playlistWithName;
	
	public AddPlaylistCommand(Setter config, String url, DetailedPlaylistWithName playlistWithName) {
		super(config);
		this.url = "http://" + url;
		this.playlistWithName = playlistWithName;
	}
	
	@Override
	protected String run() {
		DetailedPlaylist playlist = Feign.builder()
				.encoder(new GsonEncoder())
				.target(DetailedPlaylist.class, url);
		playlist.post(playlistWithName);
		return "Playlist added";
	}
	
	@Override
	protected String getFallback() {
		return "Couldn't add playlist";
	}
	
}

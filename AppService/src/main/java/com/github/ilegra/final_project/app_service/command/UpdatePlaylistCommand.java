package com.github.ilegra.final_project.app_service.command;

import com.github.ilegra.final_project.app_service.feign.DetailedPlaylist;
import com.github.ilegra.final_project.app_service.feign.DetailedPlaylistWithName;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonEncoder;

public class UpdatePlaylistCommand extends HystrixCommand<Boolean> {
	
	private String url;
	private String id;
	private DetailedPlaylistWithName playlistWithName;
	
	public UpdatePlaylistCommand(Setter config, String url, String id, DetailedPlaylistWithName playlistWithName) {
		super(config);
		this.url = "http://" + url;
		this.id = id;
		this.playlistWithName = playlistWithName;
	}
	
	@Override
	protected Boolean run() {
		DetailedPlaylist playlist = Feign.builder()
				.encoder(new GsonEncoder())
				.target(DetailedPlaylist.class, url);
		return playlist.put(id, playlistWithName);
	}
	
	@Override
	protected Boolean getFallback() {
		return false;
	}
	
}

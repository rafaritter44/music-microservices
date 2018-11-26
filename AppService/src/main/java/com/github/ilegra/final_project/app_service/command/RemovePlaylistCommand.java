package com.github.ilegra.final_project.app_service.command;

import com.github.ilegra.final_project.app_service.feign.DetailedPlaylist;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;

public class RemovePlaylistCommand extends HystrixCommand<Boolean> {
	
	private String url;
	private String id;
	
	public RemovePlaylistCommand(Setter config, String url, String id) {
		super(config);
		this.url = "http://" + url;
		this.id = id;
	}
	
	@Override
	protected Boolean run() {
		DetailedPlaylist playlist = Feign.builder().target(DetailedPlaylist.class, url);
		return playlist.delete(id);
	}
	
	@Override
	protected Boolean getFallback() {
		return false;
	}
	
}

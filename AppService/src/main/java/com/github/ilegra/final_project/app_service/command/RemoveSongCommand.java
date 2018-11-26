package com.github.ilegra.final_project.app_service.command;

import com.github.ilegra.final_project.app_service.feign.Song;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;

public class RemoveSongCommand extends HystrixCommand<Boolean> {
	
	private String url;
	private String id;
	
	public RemoveSongCommand(Setter config, String url, String id) {
		super(config);
		this.url = "http://" + url;
		this.id = id;
	}
	
	@Override
	protected Boolean run() {
		Song song = Feign.builder().target(Song.class, url);
		return song.delete(id);
	}
	
	@Override
	protected Boolean getFallback() {
		return false;
	}
	
}

package com.github.ilegra.final_project.app_service.command;

import com.github.ilegra.final_project.app_service.feign.Song;
import com.github.ilegra.final_project.app_service.feign.SongPost;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonEncoder;

public class AddSongCommand extends HystrixCommand<String> {

	private String url;
	private SongPost songPost;
	
	public AddSongCommand(Setter config, String url, SongPost songPost) {
		super(config);
		this.url = "http://" + url;
		this.songPost = songPost;
	}
	
	@Override
	protected String run() {
		Song song = Feign.builder()
    			.encoder(new GsonEncoder())
    			.target(Song.class, url);
        song.post(songPost);
		return "Song added";
	}
	
	@Override
	protected String getFallback() {
		return "Couldn't add song";
	}
	
}

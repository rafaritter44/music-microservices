package com.github.ilegra.final_project.app_service.command;

import com.github.ilegra.final_project.app_service.feign.Song;
import com.github.ilegra.final_project.app_service.feign.SongImpl;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonEncoder;

public class AddSongCommand extends HystrixCommand<Void> {

	private String url;
	private SongImpl songImpl;
	
	public AddSongCommand(Setter config, String url, SongImpl songImpl) {
		super(config);
		this.url = "http://" + url;
		this.songImpl = songImpl;
	}
	
	@Override
	protected Void run() {
		Song song = Feign.builder()
    			.encoder(new GsonEncoder())
    			.target(Song.class, url);
        song.post(songImpl);
		return null;
	}
	
}

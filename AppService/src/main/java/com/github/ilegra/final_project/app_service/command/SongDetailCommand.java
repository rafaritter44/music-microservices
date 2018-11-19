package com.github.ilegra.final_project.app_service.command;

import java.util.Optional;

import com.github.ilegra.final_project.app_service.feign.Song;
import com.github.ilegra.final_project.app_service.feign.SongImpl;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonDecoder;

public class SongDetailCommand extends HystrixCommand<Optional<SongImpl>> {

	private String url;
	private String songID;
	
    public SongDetailCommand(Setter config, String url, String songID) {
        super(config);
        this.url = "http://" + url;
        this.songID = songID;
    }

    @Override
    protected Optional<SongImpl> run() {
    	Song song = Feign.builder()
    			.decoder(new GsonDecoder())
    			.target(Song.class, url);
        return Optional.ofNullable(song.get(songID));
    }

    @Override
    protected Optional<SongImpl> getFallback() {
        return Optional.empty();
    }
}

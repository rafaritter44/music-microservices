package com.github.ilegra.final_project.app_service.command;

import java.util.Optional;

import com.github.ilegra.final_project.app_service.feign.DetailedPlaylist;
import com.github.ilegra.final_project.app_service.feign.DetailedPlaylistImpl;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonDecoder;

public class DetailedPlaylistCommand extends HystrixCommand<Optional<DetailedPlaylistImpl>> {
	
	private String url;
	private String playlistID;
	
	public DetailedPlaylistCommand(Setter config, String url, String playlistID) {
        super(config);
        this.url = "http://" + url;
        this.playlistID = playlistID;
    }
	
	@Override
    protected Optional<DetailedPlaylistImpl> run() {
    	DetailedPlaylist playlist = Feign.builder()
    			.decoder(new GsonDecoder())
    			.target(DetailedPlaylist.class, url);
        return Optional.ofNullable(playlist.get(playlistID));
    }

    @Override
    protected Optional<DetailedPlaylistImpl> getFallback() {
        return Optional.empty();
    }

}

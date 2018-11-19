package com.github.ilegra.final_project.app_service.command;

import java.util.ArrayList;
import java.util.List;

import com.github.ilegra.final_project.app_service.feign.Playlist;
import com.github.ilegra.final_project.app_service.feign.User;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.gson.GsonDecoder;

public class PlaylistsCommand extends HystrixCommand<List<Playlist>> {

	private String url;
	private String userID;

	public PlaylistsCommand(Setter config, String url, String userID) {
		super(config);
		this.url = "http://" + url;
		this.userID = userID;
	}

	@Override
	protected List<Playlist> run() {
		User user = Feign.builder()
				.decoder(new GsonDecoder()).
				target(User.class, url);
		return user.playlists(userID);
	}

	@Override
	protected List<Playlist> getFallback() {
		return new ArrayList<>();
	}

}

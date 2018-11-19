package com.github.ilegra.final_project.app_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ilegra.final_project.app_service.command.Config;
import com.github.ilegra.final_project.app_service.command.PlaylistsCommand;
import com.github.ilegra.final_project.app_service.eureka.Discovery;
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
		loadBalancer = new LoadBalancer(discovery.getIPsAndPorts(SERVICE));
		PlaylistsCommand command = new PlaylistsCommand(
				config.getCommandConfig(SERVICE), loadBalancer.getURL(), userID);
		return command.execute();
	}
	
}

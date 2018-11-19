package com.github.ilegra.final_project.app_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ilegra.final_project.app_service.exception.NotFoundException;
import com.github.ilegra.final_project.app_service.feign.SongImpl;
import com.github.ilegra.final_project.app_service.service.PlaylistService;
import com.github.ilegra.final_project.app_service.service.SongService;
import com.google.gson.Gson;

@Controller
@RestController
@EnableAutoConfiguration
public class AppController {
	
	@Autowired
	private SongService songService;
	@Autowired
	private PlaylistService playlistService;
	
	@RequestMapping("/app-service/playlists/{id}/songs")
	@ResponseBody
	public ResponseEntity<String> detailPlaylist(@PathVariable("id") String id) {
		List<SongImpl> songs;
		try {
			songs = songService.detailPlaylist(id);
		} catch(NotFoundException exception) {
			exception.printStackTrace();
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new Gson().toJson(songs), HttpStatus.OK);
	}
	
	@RequestMapping("/app-service/users/{id}/playlists")
	@ResponseBody
	public ResponseEntity<String> listPlaylists(@PathVariable("id") String id) {
		return new ResponseEntity<>(new Gson().toJson(playlistService.listPlaylists(id)),
				HttpStatus.OK);
	}
	
}

package com.github.ilegra.final_project.playlist_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ilegra.final_project.playlist_service.dto.RequestPlaylistDTO;
import com.github.ilegra.final_project.playlist_service.exception.InvalidIdException;
import com.github.ilegra.final_project.playlist_service.service.PlaylistService;
import com.google.gson.Gson;

@RestController
public class PlaylistController {
	private static final String DEFAULT_ERROR_MESAGE = "An unexpected error occured, please try again later";
	@Autowired
	private PlaylistService playlistService;

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("/users/{userId}/playlists")
	public ResponseEntity<String> getUserPlaylists(@PathVariable("userId") int userId) {
		try {
			String responseBody = new Gson().toJson(playlistService.getUserPlaylists(userId));
			return new ResponseEntity<String>(responseBody, HttpStatus.OK);
		} catch (InvalidIdException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(DEFAULT_ERROR_MESAGE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/playlists/{playlistId}")
	public ResponseEntity<String> getPlaylist(@PathVariable("playlistId") int playlistId) {
		try {
			String responseBody = new Gson().toJson(playlistService.getPlayList(playlistId));
			return new ResponseEntity<String>(responseBody, HttpStatus.OK);
		} catch (InvalidIdException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(DEFAULT_ERROR_MESAGE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/playlists")
	public ResponseEntity<String> insertPlaylist(@RequestBody RequestPlaylistDTO playlist ) {
		return null;
	}
	
	@DeleteMapping("/playlists/{id}")
	public ResponseEntity<String> deletePlaylist() {
		return null;
	}
	
	@PutMapping("/playlists/{id}")
	public ResponseEntity<String> updatePlaylists(@RequestBody RequestPlaylistDTO playlist){
		return null;	
	}
}

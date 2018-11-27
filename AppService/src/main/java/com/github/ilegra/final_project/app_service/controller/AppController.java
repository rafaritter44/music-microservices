package com.github.ilegra.final_project.app_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ilegra.final_project.app_service.exception.NotFoundException;
import com.github.ilegra.final_project.app_service.feign.DetailedPlaylistWithName;
import com.github.ilegra.final_project.app_service.feign.SongImpl;
import com.github.ilegra.final_project.app_service.feign.SongPost;
import com.github.ilegra.final_project.app_service.service.PlaylistService;
import com.github.ilegra.final_project.app_service.service.SongService;
import com.google.gson.Gson;

@Controller
@RestController
@EnableAutoConfiguration
@RequestMapping("/app-service")
public class AppController {
	
	@Autowired
	private SongService songService;
	@Autowired
	private PlaylistService playlistService;
	
	@RequestMapping("/playlists/{id}/songs")
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
	
	@RequestMapping("/users/{id}/playlists")
	@ResponseBody
	public ResponseEntity<String> listPlaylists(@PathVariable("id") String id) {
		return new ResponseEntity<>(new Gson().toJson(playlistService.listPlaylists(id)),
				HttpStatus.OK);
	}
	
	@PostMapping("/songs")
	@ResponseBody
	public ResponseEntity<String> addSong(@RequestBody SongPost song) {
		String response = songService.addSong(song);
		return new ResponseEntity<>(response, response.equals("Song added") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/songs/{id}")
	@ResponseBody
	public ResponseEntity<String> removeSong(@PathVariable("id") String id) {
		return new ResponseEntity<>(songService.removeSong(id) ? "Song removed" : "Couldn't remove song", HttpStatus.OK);
	}
	
	@PostMapping("/playlists")
	@ResponseBody
	public ResponseEntity<String> addPlaylist(@RequestBody DetailedPlaylistWithName playlist) {
		String response = playlistService.addPlaylist(playlist);
		return new ResponseEntity<>(response, response.equals("Playlist added") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/playlists/{id}")
	@ResponseBody
	public ResponseEntity<String> removePlaylist(@PathVariable("id") String id) {
		return new ResponseEntity<>(playlistService.removePlaylist(id) ? "Playlist removed" : "Couldn't remove playlist", HttpStatus.OK);
	}
	
	@PutMapping("/playlists/{id}")
	@ResponseBody
	public ResponseEntity<String> updatePlaylist(@PathVariable("id") String id, @RequestBody DetailedPlaylistWithName playlist) {
		return new ResponseEntity<>(playlistService.updatePlaylist(id, playlist) ? "Playlist updated" : "Couldn't update playlist",
				HttpStatus.OK);
	}
	
}

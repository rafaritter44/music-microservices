package com.github.ilegra.final_project.song_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ilegra.final_project.song_service.command.RemoveSongCommand;
import com.github.ilegra.final_project.song_service.exception.InvalidSongId;
import com.github.ilegra.final_project.song_service.model.Song;
import com.github.ilegra.final_project.song_service.service.SongService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/song-service")
public class SongController {

	@Autowired
	private SongService songService;
	
    @GetMapping("/songs/{id}")
    @ResponseBody
    public ResponseEntity<String> detailSong(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(new Gson().toJson(songService.detailSong(id)));
        } catch (InvalidSongId exception) {
        	exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
    
  
    @PostMapping("/songs")
    @ResponseBody
    public ResponseEntity<String> addSong(@RequestBody Song song) {
    	try {
    		songService.addSong(song);
    	} catch(Exception exception) {
    		exception.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    	}
    	return ResponseEntity.ok("Your song was successfully added!");
    }
    
    @DeleteMapping("/songs/{id}")
    @ResponseBody
    public ResponseEntity<String> removeSong(@PathVariable("id") int id) {
    	return songService.removeSong(id)
    			? ResponseEntity.ok("Your song was successfully deleted!")
    			: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: Song could not be deleted");
    }
    
}

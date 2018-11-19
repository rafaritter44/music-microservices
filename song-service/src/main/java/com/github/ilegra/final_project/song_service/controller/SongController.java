package com.github.ilegra.final_project.song_service.controller;

import com.github.ilegra.final_project.song_service.exception.InvalidSongId;
import com.github.ilegra.final_project.song_service.service.SongService;
import com.google.gson.Gson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongController {

    @RequestMapping("song-service/songs/{id}")
    @ResponseBody
    public ResponseEntity detailSong (@PathVariable("id") int id) {
        SongService songService = new SongService();
        Gson gson = new Gson();

        try {
            return ResponseEntity.ok(gson.toJson(songService.execute(id)));
        } catch (InvalidSongId exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: song not found");
        }
    }
}

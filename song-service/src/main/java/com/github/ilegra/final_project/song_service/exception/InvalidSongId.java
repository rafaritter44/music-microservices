package com.github.ilegra.final_project.song_service.exception;

public class InvalidSongId extends RuntimeException {

    public InvalidSongId(String message) {
        super(message);
    }
}

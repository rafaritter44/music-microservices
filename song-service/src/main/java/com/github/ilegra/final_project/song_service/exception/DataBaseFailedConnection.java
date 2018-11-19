package com.github.ilegra.final_project.song_service.exception;

public class DataBaseFailedConnection extends RuntimeException {

    public DataBaseFailedConnection(String message) {
        super(message);
    }
}

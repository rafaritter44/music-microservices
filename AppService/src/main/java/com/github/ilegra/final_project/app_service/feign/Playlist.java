package com.github.ilegra.final_project.app_service.feign;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Playlist {

    @JsonProperty("userId")
    private int userID;
    private int id;
    private String name;

    public Playlist(int userID, int id, String name) {
        this.userID = userID;
        this.id = id;
        this.name = name;
    }

    public Playlist() {
    }
}

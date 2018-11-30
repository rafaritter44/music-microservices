package com.github.ilegra.final_project.app_service.feign;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailedPlaylistWithName {

    private String name;
    @JsonProperty("songIdList")
    private List<String> songIdList;

    public DetailedPlaylistWithName(String name, List<String> songIdList) {
        this.name = name;
        this.songIdList = songIdList;
    }

    public DetailedPlaylistWithName() {
    }

    public String getName() {
        return name;
    }

    public List<String> getSongIdList() {
        return new ArrayList<>(songIdList);
    }

}

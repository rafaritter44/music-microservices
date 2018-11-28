package com.github.ilegra.final_project.song_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Song {

    @JsonIgnore
    private int id;
    private String name;
    private String album;
    private String singer;

    public Song(int id, String name, String album, String singer) {
        this.id = id;
        this.name = name;
        this.album = album;
        this.singer = singer;
    }

    public Song() {
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getSinger() {
        return singer;
    }

    @Override
    public String toString() {
        return "ID: " + id + "Name : " + name + " Album: " + album + " Singer: " + singer;
    }

}

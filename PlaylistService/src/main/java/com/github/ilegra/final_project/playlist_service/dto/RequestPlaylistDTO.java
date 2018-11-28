package com.github.ilegra.final_project.playlist_service.dto;

import java.util.List;

public class RequestPlaylistDTO {
	private int userId;
	private int id;
	private String name;
	private List<String> songIdList;

	 public RequestPlaylistDTO(int userId, int id, String name) {
	        this.userId = userId;
	        this.id = id;
	        this.name = name;
	    }
	 
	 public RequestPlaylistDTO() {
	     
	 }
	 
	public int getUser_id() {
		return userId;
	}

	public void setUser_id(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getSongIdLIst() {
		return songIdList;
	}

	public void setSongIdLIst(List<String> songIdList) {
		this.songIdList = songIdList;
	}
}

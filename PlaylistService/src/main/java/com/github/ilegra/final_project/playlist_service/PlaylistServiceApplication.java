package com.github.ilegra.final_project.playlist_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class PlaylistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistServiceApplication.class, args);
	}
	
	
}

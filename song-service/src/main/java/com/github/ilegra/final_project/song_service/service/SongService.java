package com.github.ilegra.final_project.song_service.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.ilegra.final_project.song_service.command.AddSongCommand;
import com.github.ilegra.final_project.song_service.command.DetailSongCommand;
import com.github.ilegra.final_project.song_service.command.RemoveSongCommand;
import com.github.ilegra.final_project.song_service.exception.InvalidSongId;
import com.github.ilegra.final_project.song_service.model.Song;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

@Service
public class SongService {
	
	private Setter config;
	
	public SongService() {
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
        		.withExecutionTimeoutInMilliseconds(5_000);
        config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("song_service"))
                .andCommandPropertiesDefaults(commandProperties);
	}

    public Song detailSong(int id) throws InvalidSongId {
        Optional<Song> song = new DetailSongCommand(config, id).execute();
        if(!song.isPresent())
            throw new InvalidSongId("The song ID doesn't exist");
        return song.get();
    }
    
    public void addSong(Song song) { 
    	new AddSongCommand(config, song).execute();
    }
    
    public boolean removeSong(int id) {
    	return new RemoveSongCommand(config, id).execute() != 0;
    }
    
}

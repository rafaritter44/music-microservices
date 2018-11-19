package com.github.ilegra.final_project.song_service.service;

import com.github.ilegra.final_project.song_service.command.DetailSongCommand;
import com.github.ilegra.final_project.song_service.exception.InvalidSongId;
import com.github.ilegra.final_project.song_service.model.Song;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Optional;

public class SongService {

    public Song execute(int id) throws InvalidSongId {

        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("song_service"));


        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(5_000);
        config.andCommandPropertiesDefaults(commandProperties);



        Optional<Song> optionalSong = new DetailSongCommand(config, id).execute();

        if( !optionalSong.isPresent())
            throw new InvalidSongId("The id song doesn't exists");



        return optionalSong.get();

    }


}

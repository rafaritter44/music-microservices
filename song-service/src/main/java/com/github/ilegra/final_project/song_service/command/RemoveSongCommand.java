package com.github.ilegra.final_project.song_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.github.ilegra.final_project.song_service.database.ConnectionFactory;
import com.github.ilegra.final_project.song_service.exception.DataBaseFailedConnection;
import com.github.ilegra.final_project.song_service.model.Song;
import com.netflix.hystrix.HystrixCommand;

@Component
public class RemoveSongCommand extends HystrixCommand<Integer> {

	private int id;
	
	public RemoveSongCommand(Setter config, int id) {
		super(config);
		this.id = id;
	}
	
	@Override
	protected Integer run() {
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement stmt = con.prepareStatement("DELETE FROM song WHERE id = ?"
						.replaceFirst("?", id + ""))) {
			return stmt.executeUpdate();
        } catch (Exception exception) {
            throw new DataBaseFailedConnection("Connection database failed");
        }
	}
	
	@Override
    protected Integer getFallback() {
        return 0;
    }
	
}

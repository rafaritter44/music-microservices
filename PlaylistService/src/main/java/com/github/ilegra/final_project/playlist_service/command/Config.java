package com.github.ilegra.final_project.playlist_service.command;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

@Component
public class Config {

	public Setter getCommandConfig() {

		Setter config = HystrixCommand.Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("playlist-service"));

		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
		commandProperties.withExecutionTimeoutInMilliseconds(5_000);
		config.andCommandPropertiesDefaults(commandProperties);

		return config;
	}
}

package com.github.ilegra.final_project.playlist_service.eureka;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EurekaRegistry {
	private static final String APP_ID = "playlist-service";

	@EventListener(ApplicationStartedEvent.class)
	public void registry() {
		System.out.println(EurekaConfiguration.getInstance().registerApp(APP_ID));
	}

	@Scheduled(fixedRate = 20000, initialDelay = 5000)
	public void renewLease() {
		System.out.println(EurekaConfiguration.getInstance().renewLease(APP_ID));
	}

}

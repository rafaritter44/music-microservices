package com.github.ilegra.final_project.song_service.eureka;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EurekaRegistry {
    private String appID = "song-service";

    @EventListener(ApplicationStartedEvent.class)
    public void resgistry(){
        EurekaService service = EurekaService.getInstance();
        System.out.println(service.registerApp(appID));
    }

    @Scheduled(fixedRate = 20000, initialDelay = 5000)
    public void renewLease() {
        System.out.println(EurekaService.getInstance().renewLease(appID));
    }
}

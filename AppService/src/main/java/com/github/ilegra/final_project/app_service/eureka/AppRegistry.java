package com.github.ilegra.final_project.app_service.eureka;

import java.util.Arrays;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.ilegra.final_project.app_service.config.ContextSingleton;
import com.github.ilegra.final_project.app_service.util.GetHostIp;

@Component
public class AppRegistry {
	
	private final ApplicationContext CONTEXT = ContextSingleton.getInstance();
	private final RestTemplate REST_TEMPLATE = CONTEXT.getBean(RestTemplate.class);
	private final String URL = "http://" + GetHostIp.getMachineIp() + ":8080/eureka/v2/apps/";
	private final String APP_NAME = "app-service";
	
	private HttpHeaders jsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    return headers;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void register() {
		REST_TEMPLATE.exchange(URL + APP_NAME, HttpMethod.POST,
				new HttpEntity<String>(RegistryBuilder.get(APP_NAME), jsonHeaders()), String.class);
	}
	
	private int renewLease(String appID, String appInstance) {
		HttpEntity<String> entity = new HttpEntity<String>(jsonHeaders());
		ResponseEntity<String> response =
		    		REST_TEMPLATE.exchange(
		    				URL + appID + "/" + RegistryBuilder.buildInstanceID(), HttpMethod.PUT, entity, String.class);
		return response.getStatusCodeValue();
	}
	
	@Scheduled(fixedRate = 20000, initialDelay = 5000)
    public void renewLease() {
        System.out.println(renewLease(APP_NAME, RegistryBuilder.buildInstanceID()));
    }
	
}

package com.github.ilegra.final_project.app_service.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextSingleton {
	
	private static class ContextHolder {
		static final ApplicationContext INSTANCE =
				new AnnotationConfigApplicationContext(AppConfig.class);
	}
	
	public static ApplicationContext getInstance() {
		return ContextHolder.INSTANCE;
	}
	
	private ContextSingleton() {}

}

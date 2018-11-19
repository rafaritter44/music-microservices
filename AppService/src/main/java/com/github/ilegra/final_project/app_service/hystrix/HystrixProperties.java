package com.github.ilegra.final_project.app_service.hystrix;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hystrix", ignoreUnknownFields = true)
class HystrixProperties {
    public boolean enabled = true;
    public boolean streamEnabled = false;
    public String streamUrl = "/hystrix.stream";
}
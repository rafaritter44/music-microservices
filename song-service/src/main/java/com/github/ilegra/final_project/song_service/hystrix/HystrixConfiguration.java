package com.github.ilegra.final_project.song_service.hystrix;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfiguration {
    @Bean
    public ServletRegistrationBean hystrixStreamServlet() {
        final String STREAM_URL = "/song-service/hystrix.stream";
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), STREAM_URL);
    }
}

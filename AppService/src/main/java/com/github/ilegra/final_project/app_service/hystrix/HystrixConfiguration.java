package com.github.ilegra.final_project.app_service.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@Configuration
@EnableConfigurationProperties(HystrixProperties.class)
@ConditionalOnExpression("${hystrix.enabled:true}")
public class HystrixConfiguration {

    @Autowired
    private HystrixProperties hystrixProperties;

    @Bean
    @ConditionalOnClass(HystrixCommandAspect.class)
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }

    @Bean
    @ConditionalOnClass(HystrixMetricsStreamServlet.class)
    @ConditionalOnExpression("${hystrix.streamEnabled:false}")
    public ServletRegistrationBean hystrixStreamServlet() {
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), hystrixProperties.streamUrl);
    }
}
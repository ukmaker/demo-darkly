package com.demo.darkly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableSpringHttpSession
public class DemoDarklyApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoDarklyApplication.class, args);
    }

    @Bean
    public MapSessionRepository sessionRepository() {

        return new MapSessionRepository(new ConcurrentHashMap<>());
    }

}

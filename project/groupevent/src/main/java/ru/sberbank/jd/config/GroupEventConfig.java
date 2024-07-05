package ru.sberbank.jd.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Основная конфигурация Spring приложения GroupEvent.
 */
@Slf4j
@Configuration
public class GroupEventConfig {

    @Bean
    public RestTemplate restTemplate(){
        log.info("Create bean RestTemplate");
        return new RestTemplate();
    }
}
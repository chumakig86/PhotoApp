package com.example.photoapp.config;

import com.example.photoapp.service.StringUtilService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    StringUtilService stringUtilService() {
        return new StringUtilService();
    }
}

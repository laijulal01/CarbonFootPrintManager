package com.allianz.coreader.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Co2ReaderConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

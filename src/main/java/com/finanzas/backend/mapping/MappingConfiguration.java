package com.finanzas.backend.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sound.sampled.Port;

@Configuration("projectMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }

    @Bean
    public DocumentMapper documentMapperMapper() {
        return new DocumentMapper();
    }

    @Bean
    public PortfolioMapper portfolioMapper() {
        return new PortfolioMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

}

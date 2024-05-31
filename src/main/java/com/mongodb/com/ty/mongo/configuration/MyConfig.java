package com.mongodb.com.ty.mongo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
@Component
@Configuration
public class MyConfig {

    @Bean
    public ModelMapper getModelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setCollectionsMergeEnabled(false);
        return modelMapper;
    }
}

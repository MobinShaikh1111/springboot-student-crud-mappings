package com.LearnMapping.LearnMapping.Config;

import com.LearnMapping.LearnMapping.DTO.StudentRequestDTO;
import com.LearnMapping.LearnMapping.DTO.StudentResponseDTO;
import com.LearnMapping.LearnMapping.entity.Course;
import com.LearnMapping.LearnMapping.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;


@org.springframework.context.annotation.Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Deep mapping enabled
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true);

        return modelMapper;
    }
}
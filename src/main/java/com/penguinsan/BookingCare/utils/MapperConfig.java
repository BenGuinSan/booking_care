package com.penguinsan.BookingCare.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    private static final ModelMapper modelMapper = new ModelMapper();


}

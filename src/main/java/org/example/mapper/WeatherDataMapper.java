package org.example.mapper;

import org.example.dto.foreign.CurrentWeather;
import org.example.model.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherDataMapper {
    @Mapping(target = "nanos", expression = "java(java.time.Instant.now().getEpochSecond())")
    WeatherData toEntity(CurrentWeather dto);
}

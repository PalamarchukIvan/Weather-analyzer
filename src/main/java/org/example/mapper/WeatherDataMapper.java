package org.example.mapper;

import org.example.dto.CurrentWeather;
import org.example.model.WeatherData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherDataMapper {
    WeatherData toEntity(CurrentWeather dto);
}

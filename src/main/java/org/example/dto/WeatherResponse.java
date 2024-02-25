package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WeatherResponse {
    private RequestMetaData request;
    private RequestLocation location;
    private CurrentWeather current;
}

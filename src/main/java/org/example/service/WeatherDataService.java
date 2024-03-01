package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.foreign.WeatherResponse;
import org.example.service.rest.WeatherStackRestService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherDataService {
    private final WeatherStackRestService restService;

    public WeatherResponse getWeatherData(String query, String units) {
        return restService.fetchCurrentWeatherData(query, units);
    }
}

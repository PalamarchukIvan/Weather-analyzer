package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dto.WeatherData;
import org.example.dto.WeatherResponse;
import org.example.service.WeatherDataService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {
    private final WeatherDataService dataService;
    @GetMapping
    public WeatherResponse getWeatherData(@RequestParam String query, @RequestParam(required = false, defaultValue = "m") String units) {
        return dataService.getWeatherData(query, units);
    }
}

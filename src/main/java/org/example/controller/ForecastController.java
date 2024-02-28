package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.WeatherForecastService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class ForecastController {
    private final WeatherForecastService weatherForecastService;
    @GetMapping("/predict")
    public HashMap<String, Double> forecast(@RequestParam Long locationId, @RequestParam Integer daysIntoFuture) {
        return weatherForecastService.predictWeather(locationId, daysIntoFuture);
    }
}

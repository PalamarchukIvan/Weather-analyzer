package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.WeatherData;
import org.example.dto.WeatherResponse;
import org.example.util.UrlResolver;
import org.example.util.WeatherUrl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherDataService {
    private final RestTemplate restTemplate;
    private final UrlResolver urlResolver;

    public WeatherResponse getWeatherData(String query, String units) {
        String url = urlResolver.get(WeatherUrl.current, Map.of("query", query, "units", units));
        try {
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }
}

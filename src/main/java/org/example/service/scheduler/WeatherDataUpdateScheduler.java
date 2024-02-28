package org.example.service.scheduler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.dto.foreign.WeatherResponse;
import org.example.mapper.WeatherDataMapper;
import org.example.model.Location;
import org.example.model.Settings;
import org.example.model.WeatherData;
import org.example.repository.WeatherDataRepository;
import org.example.service.WeatherRestDataService;
import org.example.service.crud.LocationsService;
import org.example.service.crud.SettingsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WeatherDataUpdateScheduler {
    private long timeout;
    private String units;
    private ScheduledExecutorService scheduler;

    private final SettingsService settingsService;
    private final LocationsService locationsService;
    private final WeatherRestDataService weatherDataService;
    private final WeatherDataMapper mapper;
    private final WeatherDataRepository weatherDataRepository;

    @PostConstruct
    private void init() {
        Settings settingsTimeout = settingsService.findByName("timeout");
        Settings settingsUnits = settingsService.findByName("units");
        timeout = Long.parseLong(settingsTimeout.getValue());
        units = settingsUnits.getValue();

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::fetchDataFromApi, 0, timeout, TimeUnit.SECONDS);
    }
    @Scheduled(fixedDelay = 5000)
    private void updateSettings() {
        units = settingsService.findByName("units").getValue();

        Settings settingsTimeout = settingsService.findByName("timeout");
        if (Long.parseLong(settingsTimeout.getValue()) != timeout) {
            scheduler.shutdown();
            scheduler.scheduleAtFixedRate(this::fetchDataFromApi, 0, timeout, TimeUnit.SECONDS);
        }
    }

    private void fetchDataFromApi() {
        List<Location> queries = locationsService.findAllActive(10);
        List<WeatherData> toSave = new ArrayList<>();

        for (Location query : queries) {
            WeatherResponse response = weatherDataService.getWeatherData(query.getQuery(), units);
            WeatherData entity = mapper.toEntity(response.getCurrent());
            entity.setLocation(query);
            toSave.add(entity);
        }
        weatherDataRepository.saveAll(toSave);
    }

}

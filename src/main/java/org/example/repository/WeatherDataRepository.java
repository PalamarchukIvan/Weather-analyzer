package org.example.repository;

import org.example.model.Location;
import org.example.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findAllByLocation(Location location);
}

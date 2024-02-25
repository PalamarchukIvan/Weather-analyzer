package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "weather_data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WeatherData {
    @Id
    private Long id;
    private String observationTime;
    private Double temperature;
    private Integer windSpeed;
    private Integer windDegree;
    private String windDir;
    private Integer pressure;
    private Integer precip;
    private Integer humidity;
    private Integer cloudcover;
    private Integer feelslike;
    private Integer uvIndex;
    private Integer visibility;
    private String isDay;

    @ManyToOne
    @ToString.Exclude
    private Location location;
}

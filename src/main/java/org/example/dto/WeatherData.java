package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WeatherData {
    @JsonProperty("observation_time")
    private String observationTime;
    private Double temperature;
    @JsonProperty("weather_code")
    private Integer weatherCode;
    @JsonProperty("weather_icons")
    private List<String> weatherIcons;
    @JsonProperty("weather_descriptions")
    private List<String> weatherDescription;
    @JsonProperty("wind_speed")
    private Integer windSpeed;
    @JsonProperty("wind_degree")
    private Integer windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    private Integer pressure;
    private Integer precip;
    private Integer humidity;
    private Integer cloudcover;
    private Integer feelslike;
    @JsonProperty("uv_index")
    private Integer uvIndex;
    private Integer visibility;
    @JsonProperty("is_day")
    private String isDay;
}

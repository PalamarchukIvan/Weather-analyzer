package org.example.dto.foreign;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.LocalTimeJsonDeserializer;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestLocation {
    private String name;
    private String country;
    private String region;
    private Double lat;
    private Double lon;
    @JsonProperty("timezone_id")
    private String timezoneId;
    @JsonProperty("localtime")
    @JsonDeserialize(using = LocalTimeJsonDeserializer.class)
    private LocalDateTime localTime;
    @JsonProperty("utc_offset")
    private Double offset;
}

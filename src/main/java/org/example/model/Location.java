package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq", allocationSize = 1)
    private Long id;
    private String query;
    private boolean isActive;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @OrderBy("observationTime desc ")
    @JsonIgnore
    private List<WeatherData> obtainedData;
}

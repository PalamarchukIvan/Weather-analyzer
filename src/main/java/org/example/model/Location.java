package org.example.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String query;
    private boolean isActive;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @OrderBy("observationTime desc ")
    private List<WeatherData> obtainedData;
}

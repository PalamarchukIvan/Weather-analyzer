package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "settings_seq")
    @SequenceGenerator(name = "settings_seq", sequenceName = "settings_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String value;
}

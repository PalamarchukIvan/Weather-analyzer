package org.example.service.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.model.Settings;
import org.example.repository.SettingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SettingsService {
    private SettingsRepository settingsRepository;

    public Settings findById(Long id) {
        return settingsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Settings findByName(String name) {
        return settingsRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public List<Settings> findAll() {
        return settingsRepository.findAll();
    }

    public List<Settings> updateSettings(List<Settings> settings) {
        return settingsRepository.saveAll(settings);
    }
}

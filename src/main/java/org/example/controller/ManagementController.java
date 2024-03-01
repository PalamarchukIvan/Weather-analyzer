package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Location;
import org.example.model.Settings;
import org.example.service.crud.LocationsService;
import org.example.service.crud.SettingsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/management", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagementController {
    private final LocationsService locationsService;
    private final SettingsService service;

    @GetMapping("/location/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationsService.findById(id);
    }

    @PostMapping("/location")
    public Location createLocation(@RequestBody Location location) {
        return locationsService.save(location);
    }

    @PutMapping("/location")
    public Location updateLocation(@RequestBody Location location) {
        return locationsService.update(location);
    }

    @DeleteMapping("/location/{id}")
    public Location removeLocationById(@PathVariable Long id) {
        return locationsService.deleteById(id);
    }

    @GetMapping("/location")
    public List<Location> findAllLocations() {
        return locationsService.findAll();
    }

    @GetMapping("/settings/{id}")
    public Settings findSettingsById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/settings/name")
    public Settings findSettingsByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @GetMapping("/settings")
    public List<Settings> findAllSettings() {
        return service.findAll();
    }

    @PutMapping("/settings")
    public List<Settings> updateSettings(List<Settings> settings) {
        return service.updateSettings(settings);
    }
}

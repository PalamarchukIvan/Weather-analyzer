package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Location;
import org.example.service.crud.LocationsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/settings", produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingController {
    private final LocationsService locationsService;
    @GetMapping("/{id}")
    public Location getById(@PathVariable Long id) {
        return locationsService.findById(id);
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return locationsService.save(location);
    }

    @PutMapping
    public Location update(@RequestBody Location location) {
        return locationsService.update(location);
    }

    @DeleteMapping("/{id}")
    public Location removeById(@PathVariable Long id) {
        return locationsService.deleteById(id);
    }

    @GetMapping
    public List<Location> findAll() {
        return locationsService.findAll();
    }
}

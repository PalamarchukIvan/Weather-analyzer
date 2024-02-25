package org.example.service.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.model.Location;
import org.example.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationsService {
    private final LocationRepository locationRepository;
    public Location save(Location location) {
        location.setId(null);
        return locationRepository.save(location);
    }
    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Location deleteById(Long id) {
        Location toDelete = findById(id);
        locationRepository.deleteById(id);
        return toDelete;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public List<Location> findAllActive() {
        return locationRepository.findAllByisActiveIsTrue();
    }
    public List<Location> findAllActive(Integer limit) {
        List<Location> allByActiveIsTrue = locationRepository.findAllByisActiveIsTrue();
        return allByActiveIsTrue.subList(0, allByActiveIsTrue.size() > limit ? limit : allByActiveIsTrue.size());
    }

    public Location update(Location location) {
        boolean exists = locationRepository.findById(location.getId()).isPresent();
        if (exists) {
            return locationRepository.save(location);
        }
        throw new IllegalArgumentException("This location does not exists");
    }
}

package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.WeatherData;
import org.example.repository.WeatherDataRepository;
import org.example.service.crud.LocationsService;
import org.example.util.WeatherDataToDatasetConverter;
import org.springframework.stereotype.Service;
import org.tribuo.Example;
import org.tribuo.Model;
import org.tribuo.MutableDataset;
import org.tribuo.Prediction;
import org.tribuo.impl.ListExample;
import org.tribuo.math.optimisers.AdaGrad;
import org.tribuo.regression.Regressor;
import org.tribuo.regression.sgd.linear.LinearSGDTrainer;
import org.tribuo.regression.sgd.objectives.SquaredLoss;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherDataRepository weatherDataRepository;
    private final LocationsService locationsService;

    @SneakyThrows
    public HashMap<String, Double> predictWeather(Long locationId, Integer daysToFuture) {
        List<WeatherData> foundData = weatherDataRepository.findAllByLocation(locationsService.findById(locationId));
        MutableDataset<Regressor> dataset = WeatherDataToDatasetConverter.convert(foundData);
        var lrada = new LinearSGDTrainer(
                new SquaredLoss(),
                new AdaGrad(0.01),
                10,
                dataset.size(),
                1,
                1L
        );
        Model<Regressor> model = lrada.train(dataset);
        Example<Regressor> example = new ListExample<>(
                new Regressor("nanos", Instant.now().plusSeconds(84_000L * daysToFuture).getEpochSecond()),
                new String[]{"nanos"},
                new double[]{Instant.now().plusSeconds(84_000L * daysToFuture).getEpochSecond()}
        );
        Prediction<Regressor> prediction = model.predict(example);
        HashMap<String, Double> result = new HashMap<>();
        for (Regressor.DimensionTuple next : prediction.getOutput()) {
            result.put(next.getName(), next.getValue());
        }
        return result;
    }
}

package org.example.util;

import org.example.model.WeatherData;
import org.tribuo.MutableDataset;
import org.tribuo.data.csv.CSVLoader;
import org.tribuo.evaluation.TrainTestSplitter;
import org.tribuo.regression.RegressionFactory;
import org.tribuo.regression.Regressor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class WeatherDataToDatasetConverter {

    public static MutableDataset<Regressor> convert(List<WeatherData> weatherDataList) throws IOException {
        String filePath = "weather-data.csv";

        writeCsvFile(weatherDataList, filePath);
        File file = new File(filePath);

        var regressionFactory = new RegressionFactory();
        var csvLoader = new CSVLoader<>(';', regressionFactory);

        var dataSource = csvLoader.loadDataSource(file.toPath(),
                Set.of("temperature,windSpeed,windDegree,pressure,precip,humidity,cloudcover,feelslike,uvIndex,visibility".split(",")),
                "nanos,temperature,windSpeed,windDegree,pressure,precip,humidity,cloudcover,feelslike,uvIndex,visibility".split(",")
        );
        var splitter = new TrainTestSplitter<>(dataSource, 1f, 0L);
        return new MutableDataset<>(splitter.getTrain());
    }

    private static void writeCsvFile(List<WeatherData> weatherDataList, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (WeatherData weatherData : weatherDataList) {
                writer.append(weatherData.getNanos() + ".0;" +
                              weatherData.getTemperature() + ";" +
                              weatherData.getWindSpeed() + ".0;" +
                              weatherData.getWindDegree() + ".0;" +
                              weatherData.getPressure() + ".0;" +
                              weatherData.getPrecip() + ".0;" +
                              weatherData.getHumidity() + ".0;" +
                              weatherData.getCloudcover() + ".0;" +
                              weatherData.getFeelslike() + ".0;" +
                              weatherData.getUvIndex() + ".0;" +
                              weatherData.getVisibility() + ".0\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
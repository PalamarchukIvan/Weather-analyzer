package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.util.UrlResolver;
import org.example.util.WeatherUrl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@AllArgsConstructor
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "weather.api")
public class UrlConfig {
    private Map<WeatherUrl, String> urls;

    @Bean
    public UrlResolver urlResolver() {
        return new UrlResolver(urls);
    }
}

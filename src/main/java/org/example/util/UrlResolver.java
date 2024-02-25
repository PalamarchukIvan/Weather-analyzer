package org.example.util;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class UrlResolver {
    private Map<WeatherUrl, String> urlMap;

    public String get(WeatherUrl key, Map<String, String> params) {

        String result = get(key);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            result = result.replace("{" + k + "}", v);
        }
        return result;
    }

    public String get(WeatherUrl key) {
        return urlMap.get(key);
    }
}

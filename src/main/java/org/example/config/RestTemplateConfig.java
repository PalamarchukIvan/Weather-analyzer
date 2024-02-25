package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RestTemplateConfig {
    @Value("${weather.api.token}")
    private String token;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        ClientHttpRequestInterceptor interceptor = getAuthInterceptor();

        template.getInterceptors().add(interceptor);
        return template;
    }

    private ClientHttpRequestInterceptor getAuthInterceptor() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            String requestUrl = request.getURI().toString();
            HttpRequest newRequest = new HttpRequest() {
                @Override
                public HttpMethod getMethod() {
                    return request.getMethod();
                }

                @Override
                public URI getURI() {
                    try {
                        String newUrl = requestUrl + "&access_key=" + token;
                        return new URI(newUrl);
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public HttpHeaders getHeaders() {
                    return request.getHeaders();
                }
            };
            return execution.execute(newRequest, body);
        };
        return interceptor;
    }
}

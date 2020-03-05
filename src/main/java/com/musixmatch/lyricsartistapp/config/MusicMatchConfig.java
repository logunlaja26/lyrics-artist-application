package com.musixmatch.lyricsartistapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class MusicMatchConfig {

    private static final MediaType PLAIN_TEXT = new MediaType("text", "plain", StandardCharsets.UTF_8);

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(PLAIN_TEXT, APPLICATION_JSON));
        restTemplate.getMessageConverters().add(0, converter);
        return restTemplate;
    }
}

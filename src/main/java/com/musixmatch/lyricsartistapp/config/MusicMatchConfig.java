package com.musixmatch.lyricsartistapp.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MusicMatchConfig {
    @Bean
    public RestTemplate getRestTemplate() {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return new RestTemplateBuilder().additionalMessageConverters(converter).build();
    }

}

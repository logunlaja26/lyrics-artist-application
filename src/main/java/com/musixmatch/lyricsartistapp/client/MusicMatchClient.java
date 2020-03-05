package com.musixmatch.lyricsartistapp.client;

import com.musixmatch.lyricsartistapp.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Component
public class MusicMatchClient {

    private RestTemplate restTemplate;

    private static final String API_KEY = "ea763ea7d9e5f955b69ed38ea84ba970";
    private static final Logger log = LoggerFactory.getLogger(MusicMatchClient.class);

    public MusicMatchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Response> getResponse(String artist, String track) {
        String[] artistStringArray = artist.split(" ");

        if (artistStringArray.length > 1) {
            for (int index = 0; index < artistStringArray.length - 1; index++) {
                artist = artistStringArray[index] + "%20" + artistStringArray[index+1];
            }
        }

        String uri = "http://api.musixmatch.com/ws/1.1/track.search?q_artist=" + artist + "&q_track=" + track + "&apikey=" + API_KEY;

        RequestEntity requestEntity = RequestEntity.get(URI.create(uri))
                .header("Content-Type", "text/plain;charset=utf-8")
                .build();
        log.info(requestEntity.getUrl().toString());
        return restTemplate.exchange(requestEntity, Response.class);
    }
}

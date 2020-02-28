package com.musixmatch.lyricsartistapp;

import com.musixmatch.lyricsartistapp.model.Message;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Component
public class MusicMatchClient {

    private RestTemplate restTemplate;

    private static final String API_KEY = "ea763ea7d9e5f955b69ed38ea84ba970";

    public MusicMatchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Message> getMessage(String artist, String track){

        //String Artist = artist;

        String uri = "http://api.musixmatch.com/ws/1.1/track.search?q_artist=" + artist + "&q_track=" + track + "&apikey=" + API_KEY;

        RequestEntity requestEntity = RequestEntity.get(URI.create(uri)).header("ContentType","application/json").build();
        return restTemplate.exchange(requestEntity, Message.class);
    }
}

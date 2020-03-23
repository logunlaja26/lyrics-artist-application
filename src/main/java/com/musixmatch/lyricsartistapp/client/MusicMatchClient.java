package com.musixmatch.lyricsartistapp.client;

import com.musixmatch.lyricsartistapp.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
public class MusicMatchClient {

    private RestTemplate restTemplate;

    private static final String MUSIX_MATCH_BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    private static final String API_KEY = "ea763ea7d9e5f955b69ed38ea84ba970";
    private static final Logger log = LoggerFactory.getLogger(MusicMatchClient.class);

    public MusicMatchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Response> getLyrics(String artist, String track) {
        long trackId = getTrackId(artist, track);

        URI lyricsUrl = UriComponentsBuilder
                .fromHttpUrl(MUSIX_MATCH_BASE_URL)
                .path("track.lyrics.get")
                .queryParam("track_id", trackId)
                .queryParam("apikey", API_KEY)
                .build()
                .toUri();

        RequestEntity lyricsRequest = RequestEntity.get(lyricsUrl)
                .header("Content-Type", "text/plain;charset=utf-8")
                .build();

        ResponseEntity<Response> lyricsResponse = restTemplate.exchange(lyricsRequest, Response.class);

        log.info(lyricsResponse.getBody().getMessage().getBody().getLyrics().getLyricsBody());
        return lyricsResponse;
    }

    private long getTrackId(String artist, String track) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(MUSIX_MATCH_BASE_URL)
                .path("track.search")
                .queryParam("q_artist", artist)
                .queryParam("q_track", track)
                .queryParam("apikey", API_KEY)
                .build()
                .toUri();

        RequestEntity requestEntity = RequestEntity.get(url)
                .header("Content-Type", "text/plain;charset=utf-8")
                .build();

        ResponseEntity<Response> tracksResponse = restTemplate.exchange(requestEntity, Response.class);

        return tracksResponse.getBody().getMessage()
                .getBody().getTrackList().get(0).getTrack().getTrackId();
    }
}

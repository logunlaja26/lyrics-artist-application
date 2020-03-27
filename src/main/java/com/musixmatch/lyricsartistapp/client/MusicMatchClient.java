package com.musixmatch.lyricsartistapp.client;

import com.musixmatch.lyricsartistapp.exception.MusicMatchException;
import com.musixmatch.lyricsartistapp.model.Response;
import com.musixmatch.lyricsartistapp.model.TrackList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Component
public class MusicMatchClient {

    private RestTemplate restTemplate;

    @Value(value = "${music.match.url}")
    private String musicMatchUrl;

    @Value(value = "${music.match.api-key}")
    private String apiKey;

    private static final Logger log = LoggerFactory.getLogger(MusicMatchClient.class);

    public MusicMatchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Response> getLyrics(String artist, String track) {
        long trackId = getTrackId(artist, track);

        URI lyricsUrl = UriComponentsBuilder
                .fromHttpUrl(musicMatchUrl)
                .path("track.lyrics.get")
                .queryParam("track_id", trackId)
                .queryParam("apikey", apiKey)
                .build()
                .toUri();

        ResponseEntity<Response> lyricsResponse = callMusicMatch(lyricsUrl);

        log.info(lyricsResponse.getBody().getMessage().getBody().getLyrics().getLyricsBody());
        return lyricsResponse;
    }

    private ResponseEntity<Response> callMusicMatch(URI uri) {
        RequestEntity request = RequestEntity.get(uri)
                .header("Content-Type", "text/plain;charset=utf-8")
                .build();

        ResponseEntity<Response> response;

        try {
            response = restTemplate.exchange(request, Response.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MusicMatchException("Response body could not be parsed.");
        }

        return response;
    }

    private long getTrackId(String artist, String track) {
        URI trackSearchUrl = UriComponentsBuilder
                .fromHttpUrl(musicMatchUrl)
                .path("track.search")
                .queryParam("q_artist", artist)
                .queryParam("q_track", track)
                .queryParam("apikey", apiKey)
                .build()
                .toUri();

        ResponseEntity<Response> tracksResponse = callMusicMatch(trackSearchUrl);

        List<TrackList> trackList = tracksResponse.getBody().getMessage().getBody().getTrackList();

        if (trackList.isEmpty()) {
            throw new MusicMatchException("Track name was not found.");
        }

        return trackList.get(0).getTrack().getTrackId();
    }
}

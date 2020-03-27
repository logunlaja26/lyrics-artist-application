package com.musixmatch.lyricsartistapp.client;

import com.musixmatch.lyricsartistapp.exception.MusicMatchException;
import com.musixmatch.lyricsartistapp.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MusicMatchClientTest {

    @Mock
    RestTemplate mockRestTemplate;

    @InjectMocks
    MusicMatchClient musicMatchClient;

    // region test data
    private String url = "http://myUrl.com";
    private String apiKey = "myKey";
    private String artist = "Bob Marley";
    private String track = "Three Little Birds";
    private long trackId = 1234L;

    private Response trackResponse = new Response(
            new Message(
                    new Body(
                            Collections.singletonList(
                                    new TrackList(
                                            new Track(
                                                    trackId, track
                                            )
                                    )
                            )
                    )

            ));

    private Response lyricsResponse = new Response(
            new Message(
                    new Body(
                            new Lyric(
                                    "Three little birds lalala"
                            )
                    )

            ));

    private RequestEntity artistAndTrackEntity = RequestEntity.get(UriComponentsBuilder
            .fromHttpUrl(url)
            .path("track.search")
            .queryParam("q_artist", artist)
            .queryParam("q_track", track)
            .queryParam("apikey", apiKey)
            .build()
            .toUri())
            .header("Content-Type", "text/plain;charset=utf-8")
            .build();

    private RequestEntity lyricsRequest = RequestEntity.get(UriComponentsBuilder
            .fromHttpUrl(url)
            .path("track.lyrics.get")
            .queryParam("track_id", trackId)
            .queryParam("apikey", apiKey)
            .build()
            .toUri())
            .header("Content-Type", "text/plain;charset=utf-8")
            .build();
    // endregion test data

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(musicMatchClient, "musicMatchUrl", url);
        ReflectionTestUtils.setField(musicMatchClient, "apiKey", apiKey);
    }

    @Test
    void getLyrics_shouldReturnBobMarleyLyrics_whenGivenBobMarleyAndThreeLittleBirds() {
        // Arrange
        when(mockRestTemplate.exchange(artistAndTrackEntity, Response.class))
                .thenReturn(ResponseEntity.ok(trackResponse));
        when(mockRestTemplate.exchange(lyricsRequest, Response.class))
                .thenReturn(ResponseEntity.ok(lyricsResponse));

        // Act
        ResponseEntity<Response> result = musicMatchClient.getLyrics(artist, track);

        // Assert
        Assertions.assertThat(result.getBody()).isEqualTo(lyricsResponse);
    }

    @Test
    void getLyrics_shouldReturnMusicMatchException_whenTrackIsNotFound() {
        // Arrange
        trackResponse.getMessage().getBody().setTrackList(emptyList());

        when(mockRestTemplate.exchange(artistAndTrackEntity, Response.class))
                .thenReturn(ResponseEntity.ok(trackResponse));

        // Act & Assert
        assertThrows(MusicMatchException.class, () -> musicMatchClient.getLyrics(artist, track));
    }

    @Test
    void getLyrics_shouldReturnThrowMusicMatchException_whenAPIKeyIsInvalid() {
        // Arrange
        trackResponse.getMessage().getBody().setTrackList(emptyList());

        when(mockRestTemplate.exchange(artistAndTrackEntity, Response.class))
                .thenThrow(new RuntimeException("API key is wrong"));

        // Act & Assert
        assertThrows(MusicMatchException.class, () -> musicMatchClient.getLyrics(artist, track));
    }

    @Test
    void getLyrics_shouldThrowMusicMatchException_whenTrackIdIsInvalid() {
        // Arrange
        trackResponse.getMessage().getBody().setTrackList(emptyList());

        when(mockRestTemplate.exchange(lyricsRequest, Response.class))
                .thenThrow(new RuntimeException("Track Id is invalid"));

        // Act & Assert
        assertThrows(MusicMatchException.class, () -> musicMatchClient.getLyrics(artist, track));
    }
}
package com.musixmatch.lyricsartistapp.controller;

import com.musixmatch.lyricsartistapp.client.MusicMatchClient;
import com.musixmatch.lyricsartistapp.model.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MusicMatchRestController {

    private MusicMatchClient musicMatchClient;

    public MusicMatchRestController(MusicMatchClient musicMatchClient) {
        this.musicMatchClient = musicMatchClient;
    }

    @GetMapping(value = "/lyrics")
    public Response getTrackId(@RequestParam("artist") String artist,
                               @RequestParam("track") String track) {
        return musicMatchClient.getResponse(artist, track).getBody();
    }
}

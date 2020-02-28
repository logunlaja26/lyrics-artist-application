package com.musixmatch.lyricsartistapp.controllers;

import com.musixmatch.lyricsartistapp.MusicMatchClient;
import com.musixmatch.lyricsartistapp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MusicMatchRestController {

    @Autowired
    private MusicMatchClient musicMatchClient;

    @GetMapping("/lyrics")
    public ResponseEntity<Message> getTrackId(@RequestParam("artist") String artist, @RequestParam("track") String track){

        return musicMatchClient.getMessage(artist,track);
    }




}

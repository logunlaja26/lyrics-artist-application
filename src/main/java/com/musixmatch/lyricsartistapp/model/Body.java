package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Body {

    @JsonProperty
    private List<Track> trackList;

    public Body(List<Track> trackList) {
        this.trackList = trackList;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}

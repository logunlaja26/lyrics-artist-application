package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    @JsonProperty("track_id")
    private int trackId;

    public Track(int trackId) {
        this.trackId = trackId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
}

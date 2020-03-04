package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    @JsonProperty("track_id")
    private int trackId;

    public Track() {
    }

    public Track(int trackId) {
        this.trackId = trackId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        return trackId == track.trackId;
    }

    @Override
    public int hashCode() {
        return trackId;
    }
}

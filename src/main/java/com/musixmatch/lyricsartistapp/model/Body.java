package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Body {

    @JsonProperty("track_list")
    private List<TrackList> trackList;

    public Body() {
    }

    public Body(List<TrackList> trackList) {
        this.trackList = trackList;
    }

    public List<TrackList> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackList> trackList) {
        this.trackList = trackList;
    }

    @Override
    public String toString() {
        return "Body{" +
                "trackList=" + trackList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Body body = (Body) o;

        return trackList.equals(body.trackList);
    }

    @Override
    public int hashCode() {
        return trackList.hashCode();
    }
}

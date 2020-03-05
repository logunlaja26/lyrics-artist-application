package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackList {
    private Track track;

    public TrackList() {
    }

    public TrackList(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackList trackList = (TrackList) o;

        return track != null ? track.equals(trackList.track) : trackList.track == null;
    }

    @Override
    public int hashCode() {
        return track != null ? track.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TrackList{" +
                "track=" + track +
                '}';
    }
}

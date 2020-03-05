package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    @JsonProperty("track_id")
    private long trackId;

    @JsonProperty("track_name")
    private String trackName;

    public Track() {
    }

    public Track(long trackId, String trackName) {
        this.trackId = trackId;
        this.trackName = trackName;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (trackId != track.trackId) return false;
        return trackName != null ? trackName.equals(track.trackName) : track.trackName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (trackId ^ (trackId >>> 32));
        result = 31 * result + (trackName != null ? trackName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                '}';
    }
}

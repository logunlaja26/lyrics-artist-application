package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {

    @JsonProperty("track_list")
    private List<TrackList> trackList;

    private Lyric lyrics;

    public Body() {
    }

    public Body(List<TrackList> trackList, Lyric lyrics) {
        this.trackList = trackList;
        this.lyrics = lyrics;
    }

    public List<TrackList> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackList> trackList) {
        this.trackList = trackList;
    }

    public Lyric getLyrics() {
        return lyrics;
    }

    public void setLyrics(Lyric lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Body body = (Body) o;

        if (trackList != null ? !trackList.equals(body.trackList) : body.trackList != null) return false;
        return lyrics != null ? lyrics.equals(body.lyrics) : body.lyrics == null;
    }

    @Override
    public int hashCode() {
        int result = trackList != null ? trackList.hashCode() : 0;
        result = 31 * result + (lyrics != null ? lyrics.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Body{" +
                "trackList=" + trackList +
                ", lyrics=" + lyrics +
                '}';
    }
}

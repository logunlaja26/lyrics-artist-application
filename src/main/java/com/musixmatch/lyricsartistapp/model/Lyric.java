package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lyric {

    @JsonProperty("lyrics_body")
    private String lyricsBody;

    public Lyric() {
    }

    public Lyric(String lyricsBody) {
        this.lyricsBody = lyricsBody;
    }

    public String getLyricsBody() {
        return lyricsBody;
    }

    public void setLyricsBody(String lyricsBody) {
        this.lyricsBody = lyricsBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lyric lyric = (Lyric) o;

        return lyricsBody != null ? lyricsBody.equals(lyric.lyricsBody) : lyric.lyricsBody == null;
    }

    @Override
    public int hashCode() {
        return lyricsBody != null ? lyricsBody.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "lyricsBody='" + lyricsBody + '\'' +
                '}';
    }
}

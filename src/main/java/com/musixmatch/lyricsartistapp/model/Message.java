package com.musixmatch.lyricsartistapp.model;

public class Message {

    private Body body;

    public Message(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}

package com.musixmatch.lyricsartistapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    private Body body;

    public Message() {
    }

    public Message(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return body.equals(message.body);
    }

    @Override
    public int hashCode() {
        return body.hashCode();
    }
}

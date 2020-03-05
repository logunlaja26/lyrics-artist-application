package com.musixmatch.lyricsartistapp.model;

public class Response {

    private Message message;

    public Response() {
    }

    public Response(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        return message != null ? message.equals(response.message) : response.message == null;
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message=" + message +
                '}';
    }
}

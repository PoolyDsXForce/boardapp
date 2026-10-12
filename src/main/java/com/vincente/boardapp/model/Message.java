package com.vincente.boardapp.model;

public class Message {
    private final String text;

    public Message(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

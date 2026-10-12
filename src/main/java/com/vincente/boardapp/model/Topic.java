package com.vincente.boardapp.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String title;
    private final List<Message> messages = new ArrayList<>();

    public Topic(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message msg) {
        if (msg == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        messages.add(msg);
    }
}

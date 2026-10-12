package com.vincente.boardapp.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Topic> topics = new ArrayList<>();

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException("Topic cannot be null");
        }
        topics.add(topic);
    }
}

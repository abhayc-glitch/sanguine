package com.backend.server.models;

import java.time.Instant;

public class Entry {

    // An Entry is where the user can do a check in, and describe there feelings Similar ot a post.java

    private String title;

    private String textContent;

    private Double entryId;

    private int mood;

    private Instant createdDate;


    public Entry(String title, String textContent, Double entryId, int mood, Instant createdDate) {
        this.title = title;
        this.textContent = textContent;
        this.entryId = entryId;
        this.mood = mood;
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = this.title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public double getEntryId() {
        return entryId;
    }

    public void setEntryId(double entryId) {
        this.entryId = entryId;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}
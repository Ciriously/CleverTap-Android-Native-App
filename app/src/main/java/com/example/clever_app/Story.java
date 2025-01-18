package com.example.clever_app;

public class Story {
    private String title;
    private boolean viewed;

    public Story(String title) {
        this.title = title;
        this.viewed = false; // Default state: not viewed
    }

    public String getTitle() {
        return title;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
    }

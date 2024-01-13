package com.example.codeandcoffee.model;

public class FeedbackDetail {
    private String FeedID;

    public String getFeedID() {
        return FeedID;
    }

    public void setFeedID(String feedID) {
        FeedID = feedID;
    }

    public FeedbackDetail() {
    }

    public FeedbackDetail(String feedID) {
        FeedID = feedID;
    }
}

package com.jd.githubtwitter;

import java.util.List;

public class GitTweet {

    private String name;

    List<String> tweets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTweets() {
        return tweets;
    }

    public void setTweets(List<String> tweets) {
        this.tweets = tweets;
    }
}

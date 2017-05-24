package com.jd.githubtwitter;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.jd.githubtwitter.Constants.ACCESS_TOKEN;
import static com.jd.githubtwitter.Constants.CONSUMER_KEY;
import static com.jd.githubtwitter.Constants.CONSUMER_SECRET;
import static com.jd.githubtwitter.Constants.TOKEN_SECRET;

public class TwitterManager extends BaseManager {

    private static volatile TwitterManager twitterManager;
    private static Object lock = new Object();

    /**
     *  Return the instance of TwitterManager
     * @return
     */
    public static TwitterManager getInstance() {
        if (twitterManager == null) {
            synchronized (lock) {
                if (twitterManager == null) {
                    twitterManager = new TwitterManager();
                }
            }
        }
        return twitterManager;
    }

    /**
     *
     * @param projectName
     * @return
     * @throws Exception
     */
    public GitTweet getTwitter(String projectName) throws Exception {
        Properties prop = getConfig();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(false)
                .setOAuthConsumerKey(prop.getProperty(CONSUMER_KEY))
                .setOAuthConsumerSecret(prop.getProperty(CONSUMER_SECRET))
                .setOAuthAccessToken(prop.getProperty(ACCESS_TOKEN))
                .setOAuthAccessTokenSecret(prop.getProperty(TOKEN_SECRET));
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        Query query = new Query(projectName);
        QueryResult result = twitter.search(query);
        GitTweet gitTweet = new GitTweet();
        List<String> tweets = new ArrayList<>();
        for (Status status : result.getTweets()) {
            tweets.add(status.getText());
        }
        gitTweet.setTweets(tweets);
        gitTweet.setName(projectName);
        return gitTweet;
    }
}

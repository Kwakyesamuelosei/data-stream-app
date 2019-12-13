package io.turntabl;

import java.util.*;


import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;
import java.util.*;

import org.apache.flume.*;


public class MyTwitterController {

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    private String keywords;

    Twitter mytwitter = null;

    MyTwitterController(Context context) {
        initialize(context);
    }

    public void initialize(Context context) {

        consumerKey = context.getString("consumerKey");
        consumerSecret = context.getString("consumerSecret");
        accessToken = context.getString("accessToken");
        accessTokenSecret = context.getString("accessTokenSecret");

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        cb.setJSONStoreEnabled(true);
        //cb.setIncludeEntitiesEnabled(true);
        String keywordString = "#sarkodie";
        keywords = keywordString.replace(",", "OR");

        mytwitter = new TwitterFactory(cb.build()).getInstance();
    }

    public List<Status> fetchTweets() {
        List<Status>allTweets = null;

        try {
            Query query = new Query(keywords);
            QueryResult result = null;
            result = mytwitter.search(query);

            allTweets = result.getTweets();

        }catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return allTweets;
    }

}

package ua.rd.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private List<Tweet> tweets;
    private List<Tweet> retweets;
    private List<Tweet> likes;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.tweets = new ArrayList<>();
        this.retweets = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public User(Long id, String name, List<Tweet> tweets, List<Tweet> retweets) {
        this.id = id;
        this.name = name;
        this.tweets = tweets;
        this.retweets = retweets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    public void deleteTweet(Tweet tweet) {
        this.tweets.remove(tweet);
    }

    public List<Tweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(List<Tweet> retweets) {
        this.retweets = retweets;
    }

    public void addRetweet(Tweet tweet) {
        this.retweets.add(tweet);
    }

    public void deleteRetweet(Tweet tweet) {
        this.retweets.remove(tweet);
    }

    public List<Tweet> getLikes() {
        return likes;
    }

    public void setLikes(List<Tweet> likes) {
        this.likes = likes;
    }

    public void likes(Tweet tweet) {
        this.likes.add(tweet);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tweets=" + tweets +
                ", retweets=" + retweets +
                ", likes=" + likes +
                '}';
    }

}

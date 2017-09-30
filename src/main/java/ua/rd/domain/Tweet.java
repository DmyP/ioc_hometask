package ua.rd.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet {
    private long id;
    private long likes;
    private String txt;
    private User user;
    private List<Tweet> replied;
    private List<String> mentions;
    private long retweets;
    private Tweet retweetedFrom;
    private LocalDateTime created;

    public Tweet() {
        this.replied = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.created = LocalDateTime.now();
    }

    public Tweet(Long id, Tweet tweet, User user) {
        this.id = id;
        this.txt = tweet.getTxt();
        this.user = user;
        this.retweetedFrom = tweet;
        this.replied = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.created = LocalDateTime.now();
        tweet.addRetweet();
        determineMentions();

    }
    public Tweet(Long id, String txt, User user) {
        this.id = id;
        this.txt = txt;
        this.user = user;
        this.replied = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.created = LocalDateTime.now();
        determineMentions();
    }

    public Tweet(Long id, String txt, User user, Tweet tweet) {
        this.id = id;
        this.txt = txt;
        this.user = user;
        this.replied = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.created = LocalDateTime.now();
        determineMentions();

    }

    public Tweet(Long id, String txt, User user, Tweet replied, List<String> mentions) {
        this.id = id;
        this.txt = txt;
        this.user = user;
        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(replied);
        this.replied = tweetList;
        this.created = LocalDateTime.now();
        this.mentions = mentions;
        determineMentions();
    }

    public void determineMentions(){
        Pattern mentionPattern = Pattern.compile("@([a-zA-Z]+)");
        Matcher matcher = mentionPattern.matcher(this.txt);
        while (matcher.find()) {
            String mention = matcher.group(1);
            mentions.add(mention);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLikes() {
        return likes;
    }

    public void addLike() {
        this.likes++;
    }

    public void removeLike() {
        this.likes--;
    }

    public long getRetweets() {
        return retweets;
    }

    public void setRetweets(long retweets) {
        this.retweets = retweets;
    }

    public void addRetweet() {
        this.retweets++;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tweet> getReplied() {
        return replied;
    }

    public void setReplied(List<Tweet> replied) {
        this.replied = replied;
    }

    public void replyTweet(Tweet tweet) {
        this.replied.add(tweet);
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (id != tweet.id) return false;
        return txt != null ? txt.equals(tweet.txt) : tweet.txt == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (txt != null ? txt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n\tTweet{" +
                "id=" + id +
                ", likes=" + likes +
                ", retweets=" + retweets +
                ", txt='" + txt + '\'' +
                ", user=" + user.getName() +
                ", replied=" + replied +
                ", mentions=" + mentions +
                ", created=" + created +
                "}";
    }



}

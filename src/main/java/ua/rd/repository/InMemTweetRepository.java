package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemTweetRepository implements TweetRepository {
    private Map<User, List<Tweet>> tweets;
    private Long maxLength;
    private AtomicLong counter = new AtomicLong(0L);

    public InMemTweetRepository() {
        this.tweets = new HashMap<>();
        this.maxLength = 100L;
    }

    public InMemTweetRepository(Map<User, List<Tweet>> tweets, long maxLength) {
        this.tweets = tweets;
        this.maxLength = maxLength;
    }

    public void setTweets(Map<User, List<Tweet>> tweets) {
        this.tweets = tweets;
    }

    public long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Collection<Tweet> getAllTweets() {
        Collection<Tweet> allTweets = new ArrayList<>();
        tweets.forEach((key, value) -> allTweets.addAll(value));
        return allTweets;
    }

    @Override
    public Collection<Tweet> getAllTweetsByUser(User user) {
        return tweets.get(user);
    }

    @Override
    public Optional<Tweet> getTweetById(Long id) {
        Collection<Tweet> allTweets = new ArrayList<>();
        tweets.forEach((key, value) -> allTweets.addAll(value));
        return allTweets.stream().filter(tweet -> Objects.equals(tweet.getId(), id)).findAny();
    }

    @Override
    public Collection<Tweet> getTweetFiltered(LocalDateTime dateBegin, LocalDateTime dateEnd) {
        if (dateBegin == null && dateEnd == null){
            return getAllTweets();
        } else if (dateBegin == null){
            return getAllTweets()
                    .stream()
                    .filter(tweet -> tweet.getCreated().isBefore(dateEnd))
                    .collect(Collectors.toList());
        } else {
            return getAllTweets()
                    .stream()
                    .filter(tweet -> tweet.getCreated().isAfter(dateBegin))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Collection<Tweet> getTweetFilteredByUser(User user, LocalDateTime dateBegin, LocalDateTime dateEnd) {
        if (dateBegin == null && dateEnd == null){
            return getAllTweets();
        } else if (dateBegin == null){
            return getAllTweets()
                    .stream()
                    .filter(tweet -> tweet.getUser().equals(user))
                    .filter(tweet -> tweet.getCreated().isBefore(dateEnd))
                    .collect(Collectors.toList());
        } else {
            return getAllTweets()
                    .stream()
                    .filter(tweet -> tweet.getUser().equals(user))
                    .filter(tweet -> tweet.getCreated().isAfter(dateBegin))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void save(User user, Tweet tweet) {
        tweet.setId(counter.getAndIncrement());
        if (!tweets.containsKey(user)) {
            List<Tweet> tweetList = new ArrayList<>();
            tweetList.add(tweet);
            tweets.put(user, tweetList);
        } else {
            tweets.get(user).add(tweet);
        }
        user.addTweet(tweet);
    }

    @Override
    public void update(User user, Tweet tweet) {
        save(user, tweet);
    }

    @Override
    public void delete(User user, Tweet tweet) {
        if (tweets.containsKey(user)) {
            List<Tweet> userTweets = tweets.get(user);
            userTweets.remove(tweet);
            tweets.put(user, userTweets);
        }
    }

    @Override
    public Tweet reply(User user, Tweet tweet, String txt) {
        Tweet newTweet = new Tweet(counter.get(), txt, user);
        tweet.replyTweet(newTweet);
        save(user, newTweet);
        return newTweet;
    }

    @Override
    public void addRetweet(User user, Tweet tweet) {
        Tweet newTweet = new Tweet(counter.get(), tweet, user);
        save(user, newTweet);
    }

    @Override
    public Tweet newTweet(User user, String txt) {
        Tweet tweet = new Tweet(counter.get(), txt, user);
        save(user, tweet);
        return tweet;
    }

}

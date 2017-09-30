package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface TweetService {
    Collection<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User createUser(String name);


    Collection<Tweet> getAllTweets();

    Collection<Tweet> getAllTweetsByUser(User user);

    Optional<Tweet> getTweetById(Long id);

    Collection<Tweet> getTweetFiltered(LocalDateTime dateBegin, LocalDateTime dateEnd);

    Collection<Tweet> getTweetFilteredByUser(User user, LocalDateTime dateBegin, LocalDateTime dateEnd);

    void saveTweet(User user, Tweet tweet);

    void updateTweet(User user, Tweet tweet);

    void deleteTweet(User user, Tweet tweet);

    Tweet replyTweet(User user, Tweet tweet, String txt);

    long getLikesNumber(Tweet tweet);

    void addLike(User user, Tweet tweet);

    void removeLike(Tweet tweet);

    void addRetweet(User user, Tweet tweet);

    long getRetweetsNumber(Tweet tweet);

    void userTimeLine(User user);

    Tweet newTweet(User user, String txt);
}

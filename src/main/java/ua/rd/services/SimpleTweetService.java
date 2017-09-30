package ua.rd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service("tweetService")
public class SimpleTweetService implements TweetService{

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    public SimpleTweetService() {
    }

    public SimpleTweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public TweetRepository getTweetRepository() {
        return tweetRepository;
    }

    public void setTweetRepository(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User createUser(String name) {
        User user = userRepository.createUser(name);
        return user;
    }

    @Override
    public Collection<Tweet> getAllTweets() {
        return tweetRepository.getAllTweets();
    }

    @Override
    public Collection<Tweet> getAllTweetsByUser(User user) {
        return tweetRepository.getAllTweetsByUser(user);
    }

    @Override
    public Optional<Tweet> getTweetById(Long id) {
        return tweetRepository.getTweetById(id);
    }

    @Override
    public Collection<Tweet> getTweetFiltered(LocalDateTime dateBegin, LocalDateTime dateEnd) {
        return tweetRepository.getTweetFiltered(dateBegin, dateEnd);
    }

    @Override
    public Collection<Tweet> getTweetFilteredByUser(User user, LocalDateTime dateBegin, LocalDateTime dateEnd) {
        return tweetRepository.getTweetFilteredByUser(user, dateBegin, dateEnd);
    }

    @Override
    public void saveTweet(User user, Tweet tweet) {
        tweetRepository.save(user, tweet);
        user.addTweet(tweet);
    }

    @Override
    public void updateTweet(User user, Tweet tweet) {
        tweetRepository.update(user, tweet);
    }

    @Override
    public void deleteTweet(User user, Tweet tweet) {
        tweetRepository.delete(user, tweet);
        user.deleteTweet(tweet);
    }

    @Override
    public Tweet replyTweet(User user, Tweet tweet, String txt) {
        return tweetRepository.reply(user, tweet, txt);
    }

    @Override
    public long getLikesNumber(Tweet tweet) {
        return tweetRepository.getTweetById(tweet.getId()).get().getLikes();
    }

    @Override
    public void addLike(User user, Tweet tweet) {
        user.likes(tweet);
        tweetRepository.getTweetById(tweet.getId()).get().addLike();
    }

    @Override
    public void removeLike(Tweet tweet) {
        tweetRepository.getTweetById(tweet.getId()).get().removeLike();
    }

    @Override
    public void addRetweet(User user, Tweet tweet) {
        tweetRepository.addRetweet(user, tweet);
        user.addRetweet(tweet);
    }

    @Override
    public long getRetweetsNumber(Tweet tweet) {
        return tweetRepository.getTweetById(tweet.getId()).get().getRetweets();
    }

    @Override
    public void userTimeLine(User user) {
        System.out.println(user.getName() + " tweets - ");
        user.getTweets().forEach(System.out::println);

        System.out.println(user.getName() + " retweets - ");
        user.getRetweets().forEach(System.out::println);

        System.out.println(user.getName() + " likes  - ");
        user.getLikes().forEach(System.out::println);
    }

    @Override
    public Tweet newTweet(User user, String txt) {
        return  tweetRepository.newTweet(user, txt);
    }

}

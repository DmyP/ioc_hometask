package ua.rd.repository;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface TweetRepository {

        Collection<Tweet> getAllTweets();

        Collection<Tweet> getAllTweetsByUser(User user);

        Optional<Tweet> getTweetById(Long id);

        Collection<Tweet> getTweetFiltered(LocalDateTime dateBegin, LocalDateTime dateEnd);

        Collection<Tweet> getTweetFilteredByUser(User user, LocalDateTime dateBegin, LocalDateTime dateEnd);

        void save(User user, Tweet tweet);

        void update(User user, Tweet tweet);

        void delete(User user, Tweet tweet);

        Tweet reply(User user, Tweet tweet, String txt);

        void addRetweet(User user, Tweet tweet);

        Tweet newTweet(User user, String txt);
}

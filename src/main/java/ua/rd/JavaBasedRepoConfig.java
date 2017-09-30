package ua.rd;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.InMemTweetRepository;
import ua.rd.repository.InMemUserRepository;
import ua.rd.repository.TweetRepository;
import ua.rd.repository.UserRepository;

@Configuration
public class JavaBasedRepoConfig {

    @Bean
    @Scope("singleton")
    public Tweet tweet(){
        return new Tweet();
    }


    @Bean
    public User user(Tweet tweet){
        return new User();
    }
    @Bean
    public TweetRepository tweetRepository(){
        return new InMemTweetRepository();
    }

    @Bean
    public UserRepository userRepository(){
        return new InMemUserRepository();
    }
}

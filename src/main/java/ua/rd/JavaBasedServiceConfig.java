package ua.rd;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.rd.repository.TweetRepository;
import ua.rd.repository.UserRepository;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

@Configuration
@ComponentScan
public class JavaBasedServiceConfig {

    @Bean
    public TweetService tweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        return new SimpleTweetService(tweetRepository, userRepository);
    }
}

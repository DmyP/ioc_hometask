package ua.rd;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import ua.rd.domain.User;
import ua.rd.services.TweetService;

@Configuration
public class JavaBasedConfigRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext repoContext = new AnnotationConfigApplicationContext(JavaBasedRepoConfig.class);
        AnnotationConfigApplicationContext serviceContext = new AnnotationConfigApplicationContext();
        serviceContext.register(JavaBasedServiceConfig.class);
        serviceContext.setParent(repoContext);
        serviceContext.getEnvironment().setActiveProfiles("dev", "test");
        serviceContext.refresh();

        TweetService tweetService = serviceContext.getBean(TweetService.class);
        System.out.println("----------------------------------------------------------");
        User user1 = tweetService.createUser("user1");
        User user2= tweetService.createUser("user2");
        System.out.println("Two users created: " + tweetService.getAllUsers());
        System.out.println("----------------------------------------------------------");
        //User 1 write two tweets
        tweetService.newTweet(user1, "@My first tweet");
        tweetService.newTweet(user1, "@My second tweet");
        tweetService.newTweet(user1, "@My third tweet");
        //System.out.println("Two tweets created: " + tweetService.getAllTweets());
        System.out.println("Two tweets created for user 1 : " + tweetService.getAllUsers());
        System.out.println("----------------------------------------------------------");
        tweetService.addLike(user2, user1.getTweets().get(0));
        System.out.println("User 2 likes tweet 0 : " + tweetService.getAllUsers());
        System.out.println("----------------------------------------------------------");
        tweetService.replyTweet(user2, user1.getTweets().get(1), "Nice tweet!");
        System.out.println("User 2 replied to tweet 1 : " + tweetService.getAllUsers());
        System.out.println("----------------------------------------------------------");
        tweetService.addRetweet(user1, user2.getTweets().get(0));
        System.out.println("User 1 retweeted to User 2 tweet 0 : " + tweetService.getAllUsers());
        System.out.println("----------------------------------------------------------");
        System.out.println("User 1 timeline : ");
        tweetService.userTimeLine(user1);
        System.out.println("----------------------------------------------------------");
        System.out.println("User 2 timeline : ");
        tweetService.userTimeLine(user2);
        System.out.println("----------------------------------------------------------");

    }

}

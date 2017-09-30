package ua.rd.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public @interface MyTweet{
    String value() default "";
}
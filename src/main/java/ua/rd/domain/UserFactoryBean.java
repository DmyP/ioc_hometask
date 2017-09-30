package ua.rd.domain;

import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;

public class UserFactoryBean implements FactoryBean {

    @PostConstruct
    public void init(){
        System.out.println("dsd");
    }

    @Override
    public Object getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

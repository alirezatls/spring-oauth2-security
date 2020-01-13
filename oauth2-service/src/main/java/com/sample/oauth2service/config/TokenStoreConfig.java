package com.sample.oauth2service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

//--------------------------------------------------------------
// Redis TokenStore Configuration
// Stores Oauth2Authentication in Redis
//--------------------------------------------------------------
@Configuration
public class TokenStoreConfig {

    @Bean
    public RedisTokenStore defaultTokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

    //--------------------------------------------------------------
    // creating a ConnectionFactory
    //--------------------------------------------------------------
    private LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }
}

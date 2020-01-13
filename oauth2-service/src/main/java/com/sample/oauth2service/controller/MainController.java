package com.sample.oauth2service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MainController {

    private RedisTokenStore tokenStore;

    @Autowired
    public MainController(RedisTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @GetMapping("/api/user/{token}")
    public OAuth2Authentication getOauth2AuthenticationByToken(@PathVariable("token") String token) {
        return tokenStore.readAuthentication(token);
    }

    @GetMapping("/api/user/all")
    public Collection<OAuth2AccessToken> getAllAccessTokensByClientId() {
        return tokenStore.findTokensByClientId("client");
    }

    @DeleteMapping("/api/user/delete/{token}")
    public void removeAccessToken(@PathVariable("token") String token) {
        tokenStore.removeAccessToken(token);
    }
}

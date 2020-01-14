package com.sample.oauth2service.controller;

import com.sample.oauth2service.domain.UserDetails;
import com.sample.oauth2service.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class MainController {

    private RedisTokenStore tokenStore;
    private DefaultTokenServices services;

    @Autowired
    public MainController(RedisTokenStore tokenStore, DefaultTokenServices services) {
        this.tokenStore = tokenStore;
        this.services = services;
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

    @PutMapping("/api/user/update")
    public OAuth2AccessToken updateUsername(@RequestBody UserDetails details) {
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(details.getToken());
        OAuth2AccessToken accessToken = services.readAccessToken(details.getToken());

        if (oAuth2Authentication.getUserAuthentication().getName().equals(details.getUsername())) {
            Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
            UserInfo userInfo = (UserInfo) additionalInformation.get("details");
            userInfo.setName(details.getNewUsername());
            tokenStore.storeAccessToken(accessToken, oAuth2Authentication);
        }
        return accessToken;
    }
}

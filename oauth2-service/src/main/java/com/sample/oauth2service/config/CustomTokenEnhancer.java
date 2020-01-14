package com.sample.oauth2service.config;

import com.sample.oauth2service.domain.UserInfo;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//--------------------------------------------------------------
// Strategy for enhancing an access token before it is stored by an link AuthorizationServerTokenServices
//--------------------------------------------------------------

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String username = authentication.getUserAuthentication().getName();
        Map<String, Object> map = new HashMap<>();
        map.put("details", new UserInfo("1", username, "snow"));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}

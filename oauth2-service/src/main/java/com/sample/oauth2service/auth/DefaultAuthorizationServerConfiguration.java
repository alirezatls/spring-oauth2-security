package com.sample.oauth2service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


//--------------------------------------------------------------
// store client information inMemory
// store access token and authentication information inMemory

// @EnableAuthorizationServer MUST be in class
//--------------------------------------------------------------
@Configuration
//@EnableAuthorizationServer
public class DefaultAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    @Autowired
    public DefaultAuthorizationServerConfiguration(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //--------------------------------------------------------------
    // everyone can get a access token with credentials
    //
    // default permeation for tokenKeyAccess(), checkTokenAccess() is denyAll()
    //
    // you MUST provide a passwordEncoder. otherwise its not working
    //--------------------------------------------------------------
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    //--------------------------------------------------------------
    // config details that should be use by services
    //--------------------------------------------------------------
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .autoApprove(true)
                .scopes("read", "write")
                .resourceIds("sample-resource")
                .authorizedGrantTypes("client-credentials", "password", "refresh-token")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .accessTokenValiditySeconds(100000000)
                .refreshTokenValiditySeconds(10000000);
    }

    //--------------------------------------------------------------
    // yuo can config token store with this method
    //--------------------------------------------------------------
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
                .authenticationManager(authenticationManager);
    }
}

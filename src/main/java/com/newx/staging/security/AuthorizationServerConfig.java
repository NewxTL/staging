package com.newx.staging.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by Newx on 2017/11/7.
 */
@Configuration
@EnableResourceServer //必须
@EnableAuthorizationServer // 必须
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    UserDetailsService userDetailsService; // 引入security中提供的 UserDetailsService

    AuthenticationManager authenticationManager; // 引入security中提供的 AuthenticationManager

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_id")            // basic认证的用户名
                .secret("client_secret")            //basic认证的密码
                .authorizedGrantTypes("client_credentials")        //请求体grant_type对应的值
                .scopes("read", "write").autoApprove("read");       //请求体scope对应的值

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore(); //使用内存中的 token store
    }
}

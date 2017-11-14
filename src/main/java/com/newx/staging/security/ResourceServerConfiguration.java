package com.newx.staging.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by Newx on 2017/11/10.
 */
@Configuration
@EnableResourceServer   // 配置授权资源路径
@Order(6)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * test

     @Autowired
     TokenStore tokenStore;
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("app").stateless(false);//.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().antMatchers("/api")
                .and()
                .authorizeRequests()
                .antMatchers("/api").access("#oauth2.hasScope('read')");
    }



}

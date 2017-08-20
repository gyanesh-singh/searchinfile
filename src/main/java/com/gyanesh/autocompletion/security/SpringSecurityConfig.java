package com.gyanesh.autocompletion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Created by gyanesh.singh on 08/11/17.
 * Security related configuration provided here. In current case
 * ony one resource was exposed therefore handled just one. This can be
 * further enhanced on the basis of complexity and requirement.
 **/
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http.csrf ().disable ().authorizeRequests ()
                .anyRequest ().authenticated ()
                .and ().httpBasic ()
                .authenticationEntryPoint ( authEntryPoint );
    }

    @Autowired
    public void configureGlobal ( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication ().withUser ( "test" ).password ( "test1234" ).roles ( "ADMIN" );
    }

}
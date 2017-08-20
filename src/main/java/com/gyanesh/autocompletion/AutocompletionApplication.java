package com.gyanesh.autocompletion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by gyanesh.singh on 08/11/17.
 * This is spring boot starter
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.gyanesh.autocompletion.*")
@EnableCaching
public class AutocompletionApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( AutocompletionApplication.class, args );
    }
}

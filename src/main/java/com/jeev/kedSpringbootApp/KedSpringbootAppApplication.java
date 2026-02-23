package com.jeev.kedSpringbootApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.jeev.kedSpringbootApp")
@SpringBootApplication
@Slf4j
public class KedSpringbootAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(KedSpringbootAppApplication.class, args);

//    System.out.println("DEBUG: Connection URI is ->" + context.getEnvironment().getProperty("spring.data.mongodb.uri"));
}
}

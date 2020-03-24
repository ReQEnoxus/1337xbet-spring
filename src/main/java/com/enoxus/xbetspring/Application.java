package com.enoxus.xbetspring;

import com.enoxus.xbetspring.util.DataUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application {

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(20);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Qualifier(value = "sdfForApiDecoding")
    public SimpleDateFormat sdfForApiDecoding() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    @Autowired
    private DataUpdater dataUpdater;

    @Autowired
    private ExecutorService executorService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void startUpdater() {
//        executorService.submit(dataUpdater); // commented because api has limited usage and i don't want to pay for it (1-2 times are fine)
    }
}

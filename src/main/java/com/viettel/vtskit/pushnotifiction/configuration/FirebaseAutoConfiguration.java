package com.viettel.vtskit.pushnotifiction.configuration;

import com.viettel.vtskit.pushnotifiction.NotificationService;
import com.google.firebase.FirebaseApp;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@EnableConfigurationProperties(FirebaseProperties.class)
public class FirebaseAutoConfiguration {
    private FirebaseProperties firebaseProperties;


    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {

        ClassPathResource resource = new ClassPathResource(firebaseProperties.getGoogleCredentials());
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(resource.getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, firebaseProperties.getAppName());
        return FirebaseMessaging.getInstance(app);
    }

    @Bean
    public NotificationService notificationService(){
        return new NotificationService();
    }

    @Autowired
    public void setFirebaseProperties(FirebaseProperties firebaseProperties){
        this.firebaseProperties=firebaseProperties;
    }
}
package com.example.pushnotifiction.config;

import com.google.firebase.FirebaseApp;
import lombok.RequiredArgsConstructor;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {
    private final FirebaseProperties firebaseProperties;

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials=GoogleCredentials
                .fromStream(new FileInputStream(firebaseProperties.getGoogleCredentials()));
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }
}
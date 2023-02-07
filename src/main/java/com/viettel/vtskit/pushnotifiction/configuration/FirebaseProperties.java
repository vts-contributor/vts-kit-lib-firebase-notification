package com.viettel.vtskit.pushnotifiction.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
    @Value("${googleCredentials:}")
    private String googleCredentials;
    @Value("${appName:}")
    private String appName;

    public FirebaseProperties(){

    }

    private void validateProperties(){

    }
    @PostConstruct
    void init(){
        validateProperties();
    }

    public String getGoogleCredentials() {
        return googleCredentials;
    }

    public void setGoogleCredentials(String googleCredentials) {
        this.googleCredentials = googleCredentials;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
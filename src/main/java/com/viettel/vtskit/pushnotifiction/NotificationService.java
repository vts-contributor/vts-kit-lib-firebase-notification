package com.viettel.vtskit.pushnotifiction;

import com.viettel.vtskit.pushnotifiction.model.Notice;
import com.viettel.vtskit.pushnotifiction.model.Notices;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Slf4j
public class NotificationService {
    private FirebaseMessaging firebaseMessaging;

    public NotificationService(){

    }
    @Autowired
    public void setFirebaseMessaging(FirebaseMessaging firebaseMessaging){
        this.firebaseMessaging=firebaseMessaging;
    }

    public BatchResponse sendNotificationToListDevice(Notices notices) {
        log.info("START PUSH NOTIFICATION TO LIST DEVICE");
        // for iOS
        Aps aps = Aps.builder()
                .setSound(notices.getSoundNotification())
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(aps)
                .build();

        // for Android
        AndroidNotification androidNofi = AndroidNotification.builder()
                .setSound(notices.getSoundNotification())
                .build();

        AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(androidNofi)
                .build();
        List<String> registrationTokens=notices.getListRegistrationTokens();
        Notification notification = Notification.builder()
                .setTitle(notices.getSubject())
                .setBody(notices.getContent())
                .setImage(notices.getImage())
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(registrationTokens)
                .setNotification(notification)
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .putAllData(notices.getData())
                .build();

        BatchResponse batchResponse = null;
        try {
            batchResponse = firebaseMessaging.sendMulticast(message);
        } catch (Exception e) {
            log.info("Firebase error {}", e.getMessage());
        }
        if (batchResponse.getFailureCount() > 0) {
            List<SendResponse> responses = batchResponse.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful()) {
                    failedTokens.add(registrationTokens.get(i));
                }
            }
            log.info("List of tokens that caused failures: " + failedTokens);
        }
        log.info("END PUSH NOTIFICATION TO LIST DEVICE");
        return batchResponse;
    }
    public BatchResponse sendNotificationToDevice(Notice notice) {
        log.info("START PUSH NOTIFICATION TO DEVICE");
        // for iOS
        Aps aps = Aps.builder()
                .setSound(notice.getSoundNotification())
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(aps)
                .build();

        // for Android
        AndroidNotification androidNofi = AndroidNotification.builder()
                .setSound(notice.getSoundNotification())
                .build();

        AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(androidNofi)
                .build();
        String registrationTokens=notice.getRegistrationTokens();
        Notification notification = Notification.builder()
                .setTitle(notice.getSubject())
                .setBody(notice.getContent())
                .setImage(notice.getImage())
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .addToken(registrationTokens)
                .setNotification(notification)
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .putAllData(notice.getData())
                .build();

        BatchResponse batchResponse = null;
        try {
            batchResponse = firebaseMessaging.sendMulticast(message);
        } catch (Exception e) {
            log.info("Firebase error {}", e.getMessage());
        }
        if (batchResponse.getFailureCount() > 0) {

            log.info("Tokens fail: " + registrationTokens);
        }
        log.info("END PUSH NOTIFICATION TO DEVICE");
        return batchResponse;
    }
    public String sendNotificationByTopic(Notice notice,String topicName) {
        log.info("START PUSH NOTIFICATION TO DEVICE");
        // for iOS
        Aps aps = Aps.builder()
                .setSound(notice.getSoundNotification())
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(aps)
                .build();

        // for Android
        AndroidNotification androidNofi = AndroidNotification.builder()
                .setSound(notice.getSoundNotification())
                .build();

        AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(androidNofi)
                .build();
        String registrationTokens=notice.getRegistrationTokens();
        Notification notification = Notification.builder()
                .setTitle(notice.getSubject())
                .setBody(notice.getContent())
                .setImage(notice.getImage())
                .build();

        Message message = Message.builder()
                .setToken(registrationTokens)
                .setNotification(notification)
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .putAllData(notice.getData())
                .setTopic(topicName)
                .build();

        String response = null;
        try {
            response = firebaseMessaging.send(message);
        } catch (Exception e) {
            log.info("Firebase error {}", e.getMessage());
        }
        log.info("response = {} ", response);
        log.info("END SEND PUSH NOTIFICATION BY TOPIC");
        return response;
    }
    public TopicManagementResponse subscribeToTopic(String topicName, List<String> tokenDevice){
        log.info("START SUBSCRIBE A DEVICE TO THE TOPIC");
        // Subscribe a device to the topic
        TopicManagementResponse topicManagementResponse = null;
        try {
            topicManagementResponse = firebaseMessaging.subscribeToTopic(tokenDevice,topicName);
        } catch (Exception e) {
            log.info("Firebase error {}", e.getMessage());
        }
        if (topicManagementResponse.getFailureCount() > 0) {
            List<TopicManagementResponse.Error> responses = topicManagementResponse.getErrors();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                String reason = responses.get(i).getReason();
                failedTokens.add((reason));
            }
            log.info("Reasons fail list of tokens : " + failedTokens);
        }
        log.info("END SUBSCRIBE A DEVICE TO THE TOPIC");
        return topicManagementResponse;
    }
    public TopicManagementResponse unSubscribeToTopic(String topicName, List<String> tokenDevice){
        log.info("START UNSUBSCRIBE A DEVICE TO THE TOPIC");
        // Subscribe a device to the topic
        TopicManagementResponse topicManagementResponse = null;
        try {
            topicManagementResponse = firebaseMessaging.unsubscribeFromTopic(tokenDevice,topicName);
        } catch (Exception e) {
            log.info("Firebase error {}", e.getMessage());
        }
        if (topicManagementResponse.getFailureCount() > 0) {
            List<TopicManagementResponse.Error> responses = topicManagementResponse.getErrors();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                String reason = responses.get(i).getReason();
                failedTokens.add((reason));
            }
            log.info("Reasons fail list of tokens : " + failedTokens);
        }
        log.info("END UNSUBSCRIBE A DEVICE TO THE TOPIC");
        return topicManagementResponse;
    }



}

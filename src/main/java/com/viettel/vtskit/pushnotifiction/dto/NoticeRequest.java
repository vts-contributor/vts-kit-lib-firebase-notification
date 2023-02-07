package com.viettel.vtskit.pushnotifiction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeRequest {
    /**
     * Subject notification on firebase
     */
    private String subject;
    /**
     * Content notification on firebase
     */
    private String content;
    /**
     * url image present
     */
    private String image;

    private String soundNotification = "default";
    /**
     * Map of data
     */

    private Map<String, String> data;


    /**
     * FCM registration token
     */
    private String registrationTokens;
}
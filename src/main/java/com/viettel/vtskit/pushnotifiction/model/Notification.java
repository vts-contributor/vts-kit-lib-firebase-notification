package com.viettel.vtskit.pushnotifiction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
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
    /**
     * Map of data
     */
    private String soundNotification = "default";

    private Map<String, String> data;
}

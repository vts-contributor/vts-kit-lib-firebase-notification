package com.viettel.vtskit.pushnotifiction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends Notification {

    /**
     * FCM registration token
     */
    private String registrationTokens;
}

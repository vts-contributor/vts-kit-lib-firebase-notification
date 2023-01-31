package com.example.pushnotifiction.controller;

import com.example.pushnotifiction.domain.Notice;
import com.example.pushnotifiction.service.NotificationService;
import com.google.firebase.messaging.BatchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send-notification")
    public BatchResponse sendNotification(@RequestBody Notice notice){
        return notificationService.sendNotification(notice);
    }
}
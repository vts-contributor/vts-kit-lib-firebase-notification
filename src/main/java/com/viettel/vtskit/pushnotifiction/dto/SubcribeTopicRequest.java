package com.viettel.vtskit.pushnotifiction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcribeTopicRequest {
    private String topicName;
    private List<String> tokenDevice;
}

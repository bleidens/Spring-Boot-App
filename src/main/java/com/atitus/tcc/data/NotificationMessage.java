package com.atitus.tcc.data;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class NotificationMessage {
    private String recipientToken;
    private String title;
    private String body;
    @Nullable
    private String image;
//    @Nullable
//    private Map<String,String> data;
}

package com.ead.notification.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class NotificationCommandDto {

    private String title;
    private String message;
    private UUID userId;
}

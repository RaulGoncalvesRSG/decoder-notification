package com.ead.notification.dtos;

import com.ead.notification.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    @NotNull
    private NotificationStatus notificationStatus;
}

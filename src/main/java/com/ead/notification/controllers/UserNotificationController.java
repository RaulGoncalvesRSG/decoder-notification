package com.ead.notification.controllers;

import com.ead.notification.dtos.NotificationDto;
import com.ead.notification.models.NotificationModel;
import com.ead.notification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserNotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}/notifications")
    public ResponseEntity<Page<NotificationModel>> getAllNotificationsByUser(@PathVariable(value="userId") UUID userId,
                                                                             @PageableDefault(page = 0, size = 10, sort = "notificationId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<NotificationModel> notificationModel = notificationService.findAllNotificationsByUser(userId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(notificationModel);
    }

    @PutMapping("/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> updateNotification(@PathVariable(value="userId") UUID userId,
                                                     @PathVariable(value="notificationId") UUID notificationId,
                                                     @RequestBody @Valid NotificationDto notificationDto) {
        Optional<NotificationModel> notificationModelOptional = notificationService.findByNotificationIdAndUserId(notificationId, userId);

        if (notificationModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found!");
        }

        NotificationModel notificationModel = notificationModelOptional.get();
        notificationModel.setNotificationStatus(notificationDto.getNotificationStatus());
        notificationService.saveNotification(notificationModel);

        return ResponseEntity.status(HttpStatus.OK).body(notificationModel);
    }


}

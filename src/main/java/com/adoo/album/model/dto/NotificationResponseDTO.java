package com.adoo.album.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para la respuesta de creación de notificación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {
    private boolean success;
    private String message;
    private Long auditLogId;
    private LocalDateTime timestamp;
}

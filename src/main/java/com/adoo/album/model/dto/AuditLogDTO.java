package com.adoo.album.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para respuesta de AuditLog
 * Versión simplificada de la entidad para exponer en la API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDTO {
    private Long id;
    private Long userId;
    private String tipoEvento;
    private String detalle;
    private LocalDateTime createdAt;
}

package com.adoo.album.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO NotificationRequest
 * Representa una solicitud de notificación con los datos necesarios para auditoría.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    
    /**
     * ID del usuario que genera el evento
     */
    private Long userId;
    
    /**
     * Tipo de evento (ej: "COMPRA_PAQUETE", "INTERCAMBIO_ACEPTADO", "ALBUM_COMPLETADO")
     */
    private String tipoEvento;
    
    /**
     * Detalles adicionales del evento en formato texto
     */
    private String detalle;
}

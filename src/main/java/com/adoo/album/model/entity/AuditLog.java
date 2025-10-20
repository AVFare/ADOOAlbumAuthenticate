package com.adoo.album.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad AuditLog - Dominio
 * Registra eventos relevantes del sistema para auditoría.
 * Corresponde a la tabla audit_log en la base de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_log")
public class AuditLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * ID del usuario que generó el evento
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    /**
     * Tipo de evento
     * Ejemplos: COMPRA_PAQUETE, INTERCAMBIO_CREADO, INTERCAMBIO_ACEPTADO, 
     *           INTERCAMBIO_RECHAZADO, ALBUM_COMPLETADO, PREMIO_RECLAMADO,
     *           FIGURITA_NUEVA, REGISTRO_USUARIO
     */
    @Column(name = "tipo_evento", nullable = false, length = 50)
    private String tipoEvento;
    
    /**
     * Detalle adicional del evento en formato texto o JSON
     */
    @Column(name = "detalle", columnDefinition = "TEXT")
    private String detalle;
    
    /**
     * Fecha y hora de creación del registro
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Constructor para crear un nuevo log de auditoría
     */
    public AuditLog(Long userId, String tipoEvento, String detalle) {
        this.userId = userId;
        this.tipoEvento = tipoEvento;
        this.detalle = detalle;
        this.createdAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}

package com.adoo.album.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.adoo.album.model.entity.Sticker;

@Entity
@Table(name = "user_sticker")
public class UserSticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID de esta copia específica

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user; // Dueño actual de esta figurita

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id", nullable = false)
    private Sticker sticker; // Metadatos de la figurita

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Estado estado; // EN_COLECCION, EN_TRADE

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de obtención

    // Enum para el estado de la figurita en posesión
    public enum Estado {
        EN_COLECCION,
        DUPLICADA,
        EN_TRADE // Usada en un intercambio activo
    }

    // Getters y Setters (Omitidos para brevedad)
    // ...
}
package com.adoo.album.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID de la configuración del premio

    // Un álbum solo puede tener un tipo de premio configurado.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false, unique = true)
    private Album album; 

    @Column(nullable = false, length = 50)
    private String tipo; // Ej: "BADGE_ESPECIAL", "MONEDAS_BONUS", "MARCO_AVATAR"

    // payload_json: Almacena los detalles específicos del premio (ej., la URL del badge, 
    // la cantidad de monedas, o metadatos necesarios).
    @Lob 
    @Column(name = "payload_json")
    private String payloadJson; 
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructor customizado para facilitar la creación de configuraciones por el Admin
    public Reward(Album album, String tipo, String payloadJson) {
        this.album = album;
        this.tipo = tipo;
        this.payloadJson = payloadJson;
    }
}
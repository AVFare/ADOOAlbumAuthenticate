package com.adoo.album.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_reward", uniqueConstraints = {
    // Restricción clave: Un usuario solo puede tener un registro de premio por álbum.
    @UniqueConstraint(columnNames = {"user_id", "album_id"}) 
})
public class UserReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID de este registro de premio reclamado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user; // El usuario que completó el álbum.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album; // El álbum que fue completado.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id") // Referencia al premio configurado (opcional si el premio es genérico).
    private Reward reward; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Estado estado; // DISPONIBLE o RECLAMADO

    @Column(name = "claimed_at")
    private LocalDateTime claimedAt; // Fecha y hora en que se reclamó (auditoría).

    // Enum para el estado del premio del usuario
    public enum Estado {
        DISPONIBLE, // Álbum completo, premio pendiente de reclamo.
        RECLAMADO   // Álbum completo y premio ya reclamado (estado usado en reportes).
    }
    
    // Constructor customizado para facilitar la creación de registros
    public UserReward(Usuario user, Album album, Reward reward, Estado estado, LocalDateTime claimedAt) {
        this.user = user;
        this.album = album;
        this.reward = reward;
        this.estado = estado;
        this.claimedAt = claimedAt;
    }
}
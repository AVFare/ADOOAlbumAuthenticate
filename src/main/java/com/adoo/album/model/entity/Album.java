package com.adoo.album.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Lob // Para descripciones largas
    private String descripcion;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Dificultad dificultad; // FÁCIL, MEDIO, DIFÍCIL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_admin_id", nullable = false)
    private Usuario creador; // Debe ser un usuario con ROLE.ADMIN

    @Column(name = "total_figuritas", nullable = false)
    private Integer totalFiguritas; // Mínimo 10

    @Column(nullable = false)
    private Boolean publicado = false; // Solo se puede coleccionar si está publicado

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sticker> stickers;

    // Enum para la dificultad
    public enum Dificultad {
        FACIL,
        MEDIO,
        DIFICIL
    }

    public String getTitulo() {
        return titulo;
    }
}
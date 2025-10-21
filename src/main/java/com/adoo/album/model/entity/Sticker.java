package com.adoo.album.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sticker", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"album_id", "numero"})
})
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer numero; // Número dentro del álbum

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Rareza rareza; // COMUN, RARA, EPICA, etc.

    @Column(name = "stock_total", nullable = false)
    private Integer stockTotal; // N copias totales

    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible; // Stock actual para emisión

    @Column(name = "imagen_url", nullable = false, length = 255)
    private String imagenUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Enum para la rareza (configurable, aquí solo 3 niveles base)
    public enum Rareza {
        COMUN,
        RARA,
        EPICA
    }

    public String getNombre() { // Fixes: The method getNombre() is undefined
        return nombre;
    }
    
    public Rareza getRareza() { // Fixes: The method getRareza() is undefined
        return rareza;
    }
    
    public Integer getStockDisponible() { // Fixes: The method getStockDisponible() is undefined
        return stockDisponible;
    }

    public Integer getStockTotal() { // Fixes: The method getStockTotal() is undefined
        return stockTotal;
    }
    
    // Fixes: The method getAlbum() is undefined
    public Album getAlbum() { 
        return album;
    }

    public Long getId() {
        return id;
    }

}
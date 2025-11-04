package com.adoo.album.model.entity;

import com.adoo.album.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor 
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
public class Usuario { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RF9: Username Inmutable
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password; 

    // RF9: Perfil - Campos adicionales
    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 20)
    private int telefono;

    // RF9: Perfil - URL para la foto
    @Column(name = "avatar_url", length = 255)
    private String Avatar_url;

    // RF1: Roles y Autenticación
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role; // ADMIN, USER

    // RF9: Email y validación única
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    // RF10: Auditoría - Campo de creación
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at = new Date(System.currentTimeMillis());

    public Usuario(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}

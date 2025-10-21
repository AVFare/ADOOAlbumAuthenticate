package com.adoo.album.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor // Lombok generates the default constructor
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
public class Usuario { // Renamed from User to Usuario, as per your code

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RF9: Username Inmutable
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // Regla de Negocio: Se recomienda almacenar el hash, no el texto plano.
    // Usamos 'hash_password' para ser explícitos.
    @Column(name = "password", nullable = false, length = 100)
    private String password; 

    // RF9: Perfil - Campos adicionales
    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 20)
    private String telefono;

    // RF9: Perfil - URL para la foto
    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    // RF1: Roles y Autenticación
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role; // ADMIN, USER

    // RF9: Email y validación única
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    // RF10: Auditoría - Campo de creación
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructor customizado (sin Lombok, ya que @Data genera getters/setters)
    // Usamos el password en lugar de hashPassword.
    public Usuario(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}

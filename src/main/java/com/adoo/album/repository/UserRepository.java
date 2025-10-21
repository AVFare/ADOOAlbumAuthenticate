package com.adoo.album.repository;

import com.adoo.album.model.entity.Role;
import com.adoo.album.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    // RF1: Buscar usuario por username para login/seguridad
    Optional<Usuario> findByUsername(String username);

    // Búsqueda por email
    Optional<Usuario> findByEmail(String email);

    // Consulta para reportes (ej: contar por rol)
    long countByRole(Role role);
}
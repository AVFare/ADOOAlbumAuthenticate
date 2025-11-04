package com.adoo.album.infrastructure.persistence.repository;

import com.adoo.album.model.enums.Role;
import com.adoo.album.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    // RF1: Buscar usuario por username para login/seguridad
    Optional<Usuario> findByUsername(String username);

    // Búsqueda por email
    Optional<Usuario> findByEmail(String email);

    // Consulta para reportes (ej: contar por rol)
    long countByRole(Role role);

    // Consulta para obtener la distribución de usuarios por rol
    // Devuelve una lista de arrays: [Rol (Enum/String), Cantidad (Long)]
    @Query("SELECT u.role, COUNT(u) FROM Usuario u GROUP BY u.role")
    List<Object[]> countUsersByRole();
}
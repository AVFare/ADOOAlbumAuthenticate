package com.adoo.album.model.infrastructure;

import com.adoo.album.model.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz IAuditLogRepository - Infraestructura
 * Repositorio JPA para operaciones de persistencia de AuditLog.
 */
@Repository
public interface IAuditLogRepository extends JpaRepository<AuditLog, Long> {
    // saveAsync() se implementa en AuditRepositoryService usando @Async sobre save()
}

package com.adoo.album.service;

import com.adoo.album.model.entity.AuditLog;
import com.adoo.album.model.infrastructure.IAuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuditRepositoryService - Servicio
 * Servicio de negocio para gestionar operaciones de auditoría.
 */
@Service
@Transactional
public class AuditRepositoryService {
    
    @Autowired
    private IAuditLogRepository auditLogRepository;
    
    /**
     * Guarda un registro de auditoría de forma asíncrona
     * @param log El registro de auditoría a guardar
     */
    @Async
    public void saveAsync(AuditLog log) {
        try {
            auditLogRepository.save(log);
            System.out.println("Audit log guardado: " + log.getTipoEvento() + " - Usuario: " + log.getUserId());
        } catch (Exception e) {
            System.err.println("Error al guardar audit log: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

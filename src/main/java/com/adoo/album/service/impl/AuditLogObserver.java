package com.adoo.album.service.impl;

import com.adoo.album.core.observer.IObserver;
import com.adoo.album.model.dto.NotificationRequest;
import com.adoo.album.model.entity.AuditLog;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AuditLogObserver - Servicio
 * Implementa el patrón Observer como observador concreto.
 * Reacciona a notificaciones para registrar eventos en el sistema de auditoría.
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuditLogObserver implements IObserver {
    
    @Autowired
    private AuditRepositoryService auditRepositoryService;
    
    /**
     * Método llamado cuando el NotificationSubject notifica un evento
     * Implementación del patrón Observer
     * @param notification Los datos de la notificación
     */
    @Override
    public void onNotified(NotificationRequest notification) {
        System.out.println("AuditLogObserver: Evento recibido - " + notification.getTipoEvento());
        
        // Crear el registro de auditoría
        AuditLog log = new AuditLog(
            notification.getUserId(),
            notification.getTipoEvento(),
            notification.getDetalle()
        );
        
        // Guardar en BD de forma asíncrona
        auditRepositoryService.saveAsync(log);
    }
}

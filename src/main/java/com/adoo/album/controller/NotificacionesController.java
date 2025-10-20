package com.adoo.album.controller;

import com.adoo.album.core.interfaces.IObservable;
import com.adoo.album.core.interfaces.NotificationRequest;
import com.adoo.album.service.AuditLogObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;

/**
 * NotificacionesController - API
 * Controlador REST para gestionar notificaciones.
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificacionesController {
    
    @Autowired
    private IObservable notificationSubject;
    
    @Autowired
    private AuditLogObserver auditLogObserver;
    
    /**
     * Inicializa el observer al crear el controller
     * Se asegura que el AuditLogObserver esté suscrito al NotificationSubject
     */
    @PostConstruct
    public void init() {
        notificationSubject.addObserver(auditLogObserver);
        System.out.println("AuditLogObserver registrado en NotificationSubject");
    }
    
    /**
     * Crea una nueva notificación y la procesa
     * @param body NotificationRequest con userId, tipoEvento y detalle
     */
    @PostMapping
    public void crearNotificacion(@RequestBody NotificationRequest body) {
        // Validaciones básicas
        if (body.getUserId() == null) {
            throw new IllegalArgumentException("El userId es obligatorio");
        }
        
        if (body.getTipoEvento() == null || body.getTipoEvento().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de evento es obligatorio");
        }
        
        // Notificar a los observers (Patrón Observer)
        notificationSubject.notifyObservers(body);
    }
}

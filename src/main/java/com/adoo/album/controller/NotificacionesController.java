package com.adoo.album.controller;

import com.adoo.album.core.observer.IObservable;
import com.adoo.album.model.dto.NotificationRequest;
import com.adoo.album.service.impl.AuditLogObserver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.annotation.PostConstruct;

/**
 * Controlador REST para la gestión de notificaciones.
 */
@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
@Tag(name = "Notificaciones", description = "Endpoints para creación y gestión de notificaciones (Observer Pattern)")
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
     * Crea una nueva notificación y la procesa (Notifica a todos los observers registrados).
     */
    @Operation(
            summary = "Crear una nueva notificación",
            description = "Crea una notificación a partir de los datos enviados y la propaga a los observers registrados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la notificación (userId, tipoEvento, detalle)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NotificationRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Notificación creada correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en el cuerpo de la solicitud", content = @Content),
                    @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    @PostMapping("/create")
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

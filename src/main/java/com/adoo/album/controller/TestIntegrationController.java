package com.adoo.album.controller;

import com.adoo.album.core.interfaces.IObservable;
import com.adoo.album.core.interfaces.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestIntegrationController {
    
    @Autowired
    private IObservable notificationSubject;
    
    @PostMapping("/compra-paquete")
    public ResponseEntity<Map<String, Object>> simularCompraPaquete(@RequestParam Long userId, @RequestParam int cantidad) {
        notificationSubject.notifyObservers(
            new NotificationRequest(userId, "COMPRA_PAQUETE", "Usuario compró " + cantidad + " paquetes")
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Compra realizada y auditada");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/album-completado")
    public ResponseEntity<Map<String, Object>> simularAlbumCompletado(@RequestParam Long userId) {
        notificationSubject.notifyObservers(
            new NotificationRequest(userId, "ALBUM_COMPLETADO", "Usuario completó su álbum!")
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "¡Felicitaciones! Álbum completado y auditado");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/intercambio")
    public ResponseEntity<Map<String, Object>> simularIntercambio(
            @RequestParam Long userId1, 
            @RequestParam Long userId2) {
        notificationSubject.notifyObservers(
            new NotificationRequest(userId1, "INTERCAMBIO_ACEPTADO", "Intercambio con usuario " + userId2)
        );
        
        notificationSubject.notifyObservers(
            new NotificationRequest(userId2, "INTERCAMBIO_ACEPTADO", "Intercambio con usuario " + userId1)
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Intercambio realizado y auditado para ambos usuarios");
        return ResponseEntity.ok(response);
    }
}


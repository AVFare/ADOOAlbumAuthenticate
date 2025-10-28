package com.adoo.album.examples;

import com.adoo.album.core.interfaces.NotificationRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NotificationUsageExamples {

    private static final String NOTIFICATION_API_URL = "http://localhost:8080/api/notifications";
    private final RestTemplate restTemplate = new RestTemplate();

    public void ejemploCompraPaquete(Long userId, Long albumId, String albumNombre) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "COMPRA_PAQUETE",
            String.format("Usuario %d compró un paquete del álbum '%s' (ID: %d)", 
                userId, albumNombre, albumId)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploAlbumCompletado(Long userId, Long albumId, String albumNombre) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "ALBUM_COMPLETADO",
            String.format("¡Felicitaciones! Usuario %d completó el álbum '%s' (ID: %d)", 
                userId, albumNombre, albumId)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploIntercambioCreado(Long creadorId, Long receptorId, Long tradeId) {
        NotificationRequest notification = new NotificationRequest(
            creadorId,
            "INTERCAMBIO_CREADO",
            String.format("Usuario %d creó intercambio #%d con usuario %d", 
                creadorId, tradeId, receptorId)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploIntercambioAceptado(Long userId, Long tradeId, int cantidadFiguritas) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "INTERCAMBIO_ACEPTADO",
            String.format("Usuario %d aceptó intercambio #%d. Se intercambiaron %d figuritas", 
                userId, tradeId, cantidadFiguritas)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploPremioReclamado(Long userId, Long albumId, String tipoPremio) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "PREMIO_RECLAMADO",
            String.format("Usuario %d reclamó premio '%s' por completar álbum %d", 
                userId, tipoPremio, albumId)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploFiguritaRara(Long userId, String nombreFigurita, String rareza) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "FIGURITA_NUEVA",
            String.format("¡Usuario %d obtuvo figurita RARA! '%s' - Rareza: %s", 
                userId, nombreFigurita, rareza)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploAlbumCreado(Long adminId, Long albumId, String albumNombre) {
        NotificationRequest notification = new NotificationRequest(
            adminId,
            "ALBUM_CREADO",
            String.format("Admin %d creó nuevo álbum '%s' (ID: %d)", 
                adminId, albumNombre, albumId)
        );
        
        enviarNotificacion(notification);
    }

    public void ejemploErrorSistema(Long userId, String errorMessage) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "ERROR_SISTEMA",
            String.format("Error para usuario %d: %s", userId, errorMessage)
        );
        
        enviarNotificacion(notification);
    }

    private void enviarNotificacion(NotificationRequest notification) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<NotificationRequest> request = new HttpEntity<>(notification, headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(
                NOTIFICATION_API_URL,
                request,
                String.class
            );
            
            System.out.println("Notificación enviada exitosamente: " + response.getStatusCode());
            System.out.println("Respuesta: " + response.getBody());
            
        } catch (Exception e) {
            System.err.println("Error al enviar notificación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


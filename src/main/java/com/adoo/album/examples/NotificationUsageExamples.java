package com.adoo.album.examples;

import com.adoo.album.core.interfaces.NotificationRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Ejemplos de uso del módulo de notificaciones
 * Muestra cómo otros módulos pueden integrar y usar el sistema de notificaciones
 */
public class NotificationUsageExamples {

    private static final String NOTIFICATION_API_URL = "http://localhost:8080/api/notifications";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Ejemplo 1: Notificar cuando un usuario compra un paquete
     */
    public void ejemploCompraPaquete(Long userId, Long albumId, String albumNombre) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "COMPRA_PAQUETE",
            String.format("Usuario %d compró un paquete del álbum '%s' (ID: %d)", 
                userId, albumNombre, albumId)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 2: Notificar cuando un usuario completa un álbum
     */
    public void ejemploAlbumCompletado(Long userId, Long albumId, String albumNombre) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "ALBUM_COMPLETADO",
            String.format("¡Felicitaciones! Usuario %d completó el álbum '%s' (ID: %d)", 
                userId, albumNombre, albumId)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 3: Notificar cuando se crea un intercambio
     */
    public void ejemploIntercambioCreado(Long creadorId, Long receptorId, Long tradeId) {
        NotificationRequest notification = new NotificationRequest(
            creadorId,
            "INTERCAMBIO_CREADO",
            String.format("Usuario %d creó intercambio #%d con usuario %d", 
                creadorId, tradeId, receptorId)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 4: Notificar cuando se acepta un intercambio
     */
    public void ejemploIntercambioAceptado(Long userId, Long tradeId, int cantidadFiguritas) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "INTERCAMBIO_ACEPTADO",
            String.format("Usuario %d aceptó intercambio #%d. Se intercambiaron %d figuritas", 
                userId, tradeId, cantidadFiguritas)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 5: Notificar cuando se reclama un premio
     */
    public void ejemploPremioReclamado(Long userId, Long albumId, String tipoPremio) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "PREMIO_RECLAMADO",
            String.format("Usuario %d reclamó premio '%s' por completar álbum %d", 
                userId, tipoPremio, albumId)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 6: Notificar cuando un usuario obtiene una figurita rara
     */
    public void ejemploFiguritaRara(Long userId, String nombreFigurita, String rareza) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "FIGURITA_NUEVA",
            String.format("¡Usuario %d obtuvo figurita RARA! '%s' - Rareza: %s", 
                userId, nombreFigurita, rareza)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 7: Notificar cuando un admin crea un nuevo álbum
     */
    public void ejemploAlbumCreado(Long adminId, Long albumId, String albumNombre) {
        NotificationRequest notification = new NotificationRequest(
            adminId,
            "ALBUM_CREADO",
            String.format("Admin %d creó nuevo álbum '%s' (ID: %d)", 
                adminId, albumNombre, albumId)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Ejemplo 8: Notificar error del sistema
     */
    public void ejemploErrorSistema(Long userId, String errorMessage) {
        NotificationRequest notification = new NotificationRequest(
            userId,
            "ERROR_SISTEMA",
            String.format("Error para usuario %d: %s", userId, errorMessage)
        );
        
        enviarNotificacion(notification);
    }

    /**
     * Método auxiliar para enviar la notificación al API
     */
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

    /**
     * Ejemplo de uso directo del NotificationSubject (inyección de dependencias)
     * Este método sería usado dentro de un @Service con @Autowired
     */
    /*
    @Service
    public class EjemploService {
        
        @Autowired
        private NotificationSubject notificationSubject;
        
        @Autowired
        private AuditRepositoryService auditRepositoryService;
        
        public void procesarEvento(Long userId, String evento) {
            // Crear notificación
            NotificationRequest notification = new NotificationRequest(
                userId,
                evento,
                "Detalle del evento..."
            );
            
            // Opción 1: Notificar a observers directamente
            notificationSubject.notifyObservers(notification);
            
            // Opción 2: Guardar en auditoría directamente
            auditRepositoryService.createAndSaveAsync(
                userId,
                evento,
                "Detalle del evento..."
            );
        }
    }
    */
}

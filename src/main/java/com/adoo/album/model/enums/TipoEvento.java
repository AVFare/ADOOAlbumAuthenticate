package com.adoo.album.model.enums;

/**
 * Enum con los tipos de eventos del sistema
 * Define los eventos que se pueden registrar en la auditoría
 */
public enum TipoEvento {
    // Eventos de usuario
    REGISTRO_USUARIO("Registro de nuevo usuario"),
    LOGIN_EXITOSO("Login exitoso"),
    LOGIN_FALLIDO("Intento de login fallido"),
    ACTUALIZACION_PERFIL("Actualización de perfil de usuario"),
    
    // Eventos de álbumes
    ALBUM_CREADO("Álbum creado por admin"),
    ALBUM_PUBLICADO("Álbum publicado"),
    ALBUM_COMPLETADO("Usuario completó un álbum"),
    
    // Eventos de figuritas
    FIGURITA_NUEVA("Nueva figurita obtenida"),
    FIGURITAS_SUBIDAS("Figuritas subidas a un álbum"),
    
    // Eventos de paquetes
    COMPRA_PAQUETE("Compra de paquete de figuritas"),
    PAQUETE_ABIERTO("Paquete de figuritas abierto"),
    
    // Eventos de intercambios
    INTERCAMBIO_CREADO("Intercambio creado"),
    INTERCAMBIO_OFERTADO("Oferta de intercambio realizada"),
    INTERCAMBIO_ACEPTADO("Intercambio aceptado"),
    INTERCAMBIO_RECHAZADO("Intercambio rechazado"),
    INTERCAMBIO_CANCELADO("Intercambio cancelado"),
    INTERCAMBIO_CERRADO("Intercambio cerrado exitosamente"),
    
    // Eventos de premios
    PREMIO_RECLAMADO("Premio virtual reclamado"),
    PREMIO_DISPONIBLE("Premio disponible para reclamar"),
    
    // Eventos de sistema
    ERROR_SISTEMA("Error del sistema"),
    ACCION_ADMIN("Acción administrativa realizada");
    
    private final String descripcion;
    
    TipoEvento(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

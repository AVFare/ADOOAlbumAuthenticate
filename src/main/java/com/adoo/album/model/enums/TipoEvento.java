package com.adoo.album.model.enums;

public enum TipoEvento {
    REGISTRO_USUARIO("Registro de nuevo usuario"),
    LOGIN_EXITOSO("Login exitoso"),
    LOGIN_FALLIDO("Intento de login fallido"),
    ACTUALIZACION_PERFIL("Actualización de perfil de usuario"),
    
    ALBUM_CREADO("Álbum creado por admin"),
    ALBUM_PUBLICADO("Álbum publicado"),
    ALBUM_COMPLETADO("Usuario completó un álbum"),
    
    FIGURITA_NUEVA("Nueva figurita obtenida"),
    FIGURITAS_SUBIDAS("Figuritas subidas a un álbum"),
    
    COMPRA_PAQUETE("Compra de paquete de figuritas"),
    PAQUETE_ABIERTO("Paquete de figuritas abierto"),
    
    INTERCAMBIO_CREADO("Intercambio creado"),
    INTERCAMBIO_OFERTADO("Oferta de intercambio realizada"),
    INTERCAMBIO_ACEPTADO("Intercambio aceptado"),
    INTERCAMBIO_RECHAZADO("Intercambio rechazado"),
    INTERCAMBIO_CANCELADO("Intercambio cancelado"),
    INTERCAMBIO_CERRADO("Intercambio cerrado exitosamente"),
    
    PREMIO_RECLAMADO("Premio virtual reclamado"),
    PREMIO_DISPONIBLE("Premio disponible para reclamar"),
    
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


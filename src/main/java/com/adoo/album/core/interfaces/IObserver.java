package com.adoo.album.core.interfaces;

/**
 * Interfaz IObserver - Patrón Observer
 * Define el contrato para observadores que reaccionan a notificaciones.
 */
public interface IObserver {
    
    /**
     * Método que se ejecuta cuando el sujeto notifica un evento
     * @param notification Los datos de la notificación
     */
    void onNotified(NotificationRequest notification);
}

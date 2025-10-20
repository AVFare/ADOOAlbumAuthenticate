package com.adoo.album.core.interfaces;

/**
 * Interfaz IObservable - Patrón Observer
 * Define el contrato para sujetos observables que notifican a observadores.
 */
public interface IObservable {
    
    /**
     * Añade un observador a la lista de suscriptores
     * @param observer El observador a añadir
     */
    void addObserver(IObserver observer);
    
    /**
     * Remueve un observador de la lista de suscriptores
     * @param observer El observador a remover
     */
    void removeObserver(IObserver observer);
    
    /**
     * Notifica a todos los observadores suscritos
     * @param body El cuerpo de la notificación
     */
    void notifyObservers(NotificationRequest body);
}

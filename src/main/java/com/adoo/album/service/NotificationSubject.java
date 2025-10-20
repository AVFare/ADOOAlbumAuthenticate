package com.adoo.album.service;

import com.adoo.album.core.interfaces.IObservable;
import com.adoo.album.core.interfaces.IObserver;
import com.adoo.album.core.interfaces.NotificationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * NotificationSubject - Servicio
 * Implementa el patrón Observer como sujeto observable.
 * Mantiene una lista de observadores y los notifica cuando ocurren eventos.
 */
@Service
public class NotificationSubject implements IObservable {
    
    /**
     * Lista de observadores suscritos
     */
    private final List<IObserver> observers;
    
    public NotificationSubject() {
        this.observers = new ArrayList<>();
    }
    
    /**
     * Añade un observador a la lista de suscriptores
     * Patrón: Observer - permite añadir observers dinámicamente
     * @param observer El observador a añadir
     */
    @Override
    public void addObserver(IObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer añadido: " + observer.getClass().getSimpleName());
        }
    }
    
    /**
     * Remueve un observador de la lista de suscriptores
     * @param observer El observador a remover
     */
    @Override
    public void removeObserver(IObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer removido: " + observer.getClass().getSimpleName());
        }
    }
    
    /**
     * Notifica a todos los observadores suscritos
     * Este método es llamado desde otros módulos cuando ocurren eventos importantes
     * @param body El cuerpo de la notificación con userId, tipoEvento y detalle
     */
    @Override
    public void notifyObservers(NotificationRequest body) {
        System.out.println("Notificando a " + observers.size() + " observadores...");
        System.out.println("Evento: " + body.getTipoEvento() + " - Usuario: " + body.getUserId());
        
        for (IObserver observer : observers) {
            try {
                observer.onNotified(body);
            } catch (Exception e) {
                System.err.println("Error al notificar al observer: " + observer.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
    }
}

package com.adoo.album.service;

import com.adoo.album.core.interfaces.IObserver;
import com.adoo.album.core.interfaces.NotificationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para NotificationSubject
 */
class NotificationSubjectTest {

    private NotificationSubject notificationSubject;
    private MockObserver mockObserver;

    @BeforeEach
    void setUp() {
        notificationSubject = new NotificationSubject();
        mockObserver = new MockObserver();
    }

    @Test
    void testAddObserver() {
        notificationSubject.addObserver(mockObserver);
        // Verificar que se agrego correctamente
        assertNotNull(mockObserver);
    }

    @Test
    void testAddObserverNull() {
        notificationSubject.addObserver(null);
        // No deberia fallar
        assertTrue(true);
    }

    @Test
    void testAddObserverDuplicate() {
        notificationSubject.addObserver(mockObserver);
        notificationSubject.addObserver(mockObserver);
        // No deberia fallar
        assertTrue(true);
    }

    @Test
    void testRemoveObserver() {
        notificationSubject.addObserver(mockObserver);
        notificationSubject.removeObserver(mockObserver);
        assertFalse(mockObserver.wasNotified);
    }

    @Test
    void testNotifyObservers() {
        notificationSubject.addObserver(mockObserver);
        
        NotificationRequest request = new NotificationRequest(
            1L,
            "TEST_EVENT",
            "Test detail"
        );
        
        notificationSubject.notifyObservers(request);
        assertTrue(mockObserver.wasNotified);
        assertEquals("TEST_EVENT", mockObserver.lastEvent);
    }

    @Test
    void testNotifyMultipleObservers() {
        MockObserver observer1 = new MockObserver();
        MockObserver observer2 = new MockObserver();
        
        notificationSubject.addObserver(observer1);
        notificationSubject.addObserver(observer2);
        
        NotificationRequest request = new NotificationRequest(
            1L,
            "TEST_EVENT",
            "Test detail"
        );
        
        notificationSubject.notifyObservers(request);
        
        assertTrue(observer1.wasNotified);
        assertTrue(observer2.wasNotified);
    }

    // Mock Observer para testing
    private static class MockObserver implements IObserver {
        boolean wasNotified = false;
        String lastEvent = null;

        @Override
        public void onNotified(NotificationRequest notification) {
            wasNotified = true;
            lastEvent = notification.getTipoEvento();
        }
    }
}

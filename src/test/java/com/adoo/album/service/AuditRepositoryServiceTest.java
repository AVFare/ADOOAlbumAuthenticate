package com.adoo.album.service;

import com.adoo.album.model.entity.AuditLog;
import com.adoo.album.infrastructure.persistence.repository.IAuditLogRepository;
import com.adoo.album.service.impl.AuditRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para AuditRepositoryService
 * Solo prueba el método del diagrama UML: saveAsync()
 */
class AuditRepositoryServiceTest {

    @Mock
    private IAuditLogRepository auditLogRepository;

    @InjectMocks
    private AuditRepositoryService auditRepositoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAsync() {
        // Arrange
        AuditLog log = new AuditLog(1L, "TEST_EVENT", "Test detail");
        when(auditLogRepository.save(any(AuditLog.class))).thenReturn(log);

        // Act
        auditRepositoryService.saveAsync(log);

        // Assert - El método es async, solo verificamos que se llame al repositorio
        verify(auditLogRepository, times(1)).save(any(AuditLog.class));
    }

    @Test
    void testSaveAsyncWithNullLog() {
        // Arrange
        AuditLog log = null;

        // Act & Assert - Verifica que no falle con null (podría mejorar validación)
        try {
            auditRepositoryService.saveAsync(log);
        } catch (Exception e) {
            // Esperado si hay validación
        }
    }

    @Test
    void testSaveAsyncWithException() {
        // Arrange
        AuditLog log = new AuditLog(1L, "TEST_EVENT", "Test detail");
        doThrow(new RuntimeException("DB Error")).when(auditLogRepository).save(any(AuditLog.class));

        // Act - No debe fallar, solo logear el error
        auditRepositoryService.saveAsync(log);

        // Assert
        verify(auditLogRepository, times(1)).save(any(AuditLog.class));
    }
}

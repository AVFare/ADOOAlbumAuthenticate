-- Script SQL para crear la tabla audit_log
-- Base de datos: MySQL

CREATE TABLE IF NOT EXISTS audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    tipo_evento VARCHAR(50) NOT NULL,
    detalle TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Índices para mejorar el rendimiento de las consultas
    INDEX idx_user_id (user_id),
    INDEX idx_tipo_evento (tipo_evento),
    INDEX idx_created_at (created_at),
    INDEX idx_user_created (user_id, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Comentarios en las columnas
ALTER TABLE audit_log 
MODIFY COLUMN id BIGINT AUTO_INCREMENT COMMENT 'ID único del registro de auditoría',
MODIFY COLUMN user_id BIGINT NOT NULL COMMENT 'ID del usuario que generó el evento',
MODIFY COLUMN tipo_evento VARCHAR(50) NOT NULL COMMENT 'Tipo de evento (COMPRA_PAQUETE, INTERCAMBIO_ACEPTADO, etc.)',
MODIFY COLUMN detalle TEXT COMMENT 'Detalles adicionales del evento en formato texto o JSON',
MODIFY COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creación del registro';

-- Ejemplos de tipos de eventos comunes:
-- REGISTRO_USUARIO, LOGIN_EXITOSO, LOGIN_FALLIDO, ACTUALIZACION_PERFIL
-- ALBUM_CREADO, ALBUM_PUBLICADO, ALBUM_COMPLETADO
-- FIGURITA_NUEVA, FIGURITAS_SUBIDAS
-- COMPRA_PAQUETE, PAQUETE_ABIERTO
-- INTERCAMBIO_CREADO, INTERCAMBIO_ACEPTADO, INTERCAMBIO_RECHAZADO, INTERCAMBIO_CANCELADO, INTERCAMBIO_CERRADO
-- PREMIO_RECLAMADO, PREMIO_DISPONIBLE
-- ERROR_SISTEMA, ACCION_ADMIN

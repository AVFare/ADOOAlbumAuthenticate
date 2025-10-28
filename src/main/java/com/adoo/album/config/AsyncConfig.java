package com.adoo.album.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configuración para habilitar el procesamiento asíncrono
 * Necesario para que los métodos @Async funcionen correctamente
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    // La configuración por defecto de Spring Boot es suficiente
    // para el procesamiento asíncrono básico
}

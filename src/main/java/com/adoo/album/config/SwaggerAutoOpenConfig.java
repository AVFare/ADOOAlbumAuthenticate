package com.adoo.album.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.net.URI;
@Component
public class SwaggerAutoOpenConfig {

    @Value("${server.port:8080}")
    private int port;

    @PostConstruct
    public void openSwaggerUI() {
        try {
            String url = "http://localhost:" + port + "/swagger-ui/index.html#/";
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Swagger UI abierto automáticamente en: " + url);
            } else {
                System.out.println("No se pudo abrir el navegador automáticamente. Abrilo manualmente en: " + url);
            }
        } catch (Exception e) {
            System.out.println("Error al intentar abrir Swagger UI: " + e.getMessage());
        }
    }
}
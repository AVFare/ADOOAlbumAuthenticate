package com.adoo.album.service.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReporteTopAlbumsStrategy implements IReporteStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ReporteTopAlbumsStrategy.class);

    @Override
    public String generar() {
        logger.info("Generando reporte de Top Álbumes por completitud...");
        // Simulación: se registrarían los top álbumes más completados
        return "Reporte generado: Top Álbumes por completitud (simulado)";
    }
}

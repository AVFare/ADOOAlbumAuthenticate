package com.adoo.album.service.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReporteRareStickersStrategy implements IReporteStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ReporteRareStickersStrategy.class);

    @Override
    public String generar() {
        logger.info("Generando reporte de Figuritas más raras...");
        // Simulación: se registrarían las figuritas con menor stock o mayor rareza
        return "Reporte generado: Figuritas más raras (simulado)";
    }
}

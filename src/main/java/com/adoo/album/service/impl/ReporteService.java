package com.adoo.album.service.impl;

import com.adoo.album.model.dto.ReporteDTO;
import com.adoo.album.service.api.IReporteService;
import com.adoo.album.service.strategy.IReporteStrategy;
import org.springframework.stereotype.Service;

@Service
public class ReporteService implements IReporteService {

    // ¡Ya no necesita los 4 repositorios!
    // ¡Ya no necesita las 4 estrategias concretas!
    
    // Solo necesita la Factory.
    private final ReporteFactory reporteFactory;

    public ReporteService(ReporteFactory reporteFactory) {
        this.reporteFactory = reporteFactory;
    }

    @Override
    public ReporteDTO generarReporte(String tipoReporte) {
        // 1. Pide la estrategia a la factory
        IReporteStrategy strategy = reporteFactory.getStrategy(tipoReporte);
        
        // 2. Ejecuta la estrategia (el service no sabe ni le importa cuál es)
        return strategy.generar();
    }
    
    // ¡Sin métodos privados, sin switch!
}
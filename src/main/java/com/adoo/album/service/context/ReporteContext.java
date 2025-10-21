package com.adoo.album.service.context;

import com.adoo.album.service.strategy.IReporteStrategy;

public class ReporteContext {

    private IReporteStrategy strategy;

    public void setStrategy(IReporteStrategy strategy) {
        this.strategy = strategy;
    }

    public String ejecutarReporte() {
        if (strategy == null) {
            throw new IllegalStateException("Estrategia de reporte no definida.");
        }
        return strategy.generar();
    }
}

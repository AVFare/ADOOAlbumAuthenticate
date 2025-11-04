package com.adoo.album.service.impl;

import com.adoo.album.model.dto.ReporteDTO;
import com.adoo.album.service.api.IReporteService;
import com.adoo.album.service.strategy.IReporteStrategy;
import org.springframework.stereotype.Service;

@Service
public class ReporteService implements IReporteService {

    private final ReporteFactory reporteFactory;

    public ReporteService(ReporteFactory reporteFactory) {
        this.reporteFactory = reporteFactory;
    }

    @Override
    public ReporteDTO generarReporte(String tipoReporte) {
        IReporteStrategy strategy = reporteFactory.getStrategy(tipoReporte);
        return strategy.generar();
    }
}
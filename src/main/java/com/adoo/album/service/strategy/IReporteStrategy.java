package com.adoo.album.service.strategy;

import com.adoo.album.model.dto.ReporteDTO;

public interface IReporteStrategy {
    ReporteDTO generar();
    String getTipoReporte();
}

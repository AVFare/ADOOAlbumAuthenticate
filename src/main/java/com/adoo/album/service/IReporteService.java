package com.adoo.album.service;

import com.adoo.album.model.entity.dto.ReporteDTO;

public interface IReporteService {

    /**
     * Genera un reporte basado en el tipo especificado.
     * @param tipoReporte El tipo de reporte a generar (ej: "TOP_ALBUMS", "RARE_STICKERS").
     * @return ReporteDTO con los datos.
     */
    ReporteDTO generarReporte(String tipoReporte);
}
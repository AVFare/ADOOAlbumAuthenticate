package com.adoo.album.service;

import com.adoo.album.model.entity.dto.ReporteDTO;
import com.adoo.album.service.context.ReporteContext;
import com.adoo.album.service.strategy.IReporteStrategy;
import com.adoo.album.service.strategy.ReporteTopAlbumsStrategy;
import com.adoo.album.service.strategy.ReporteRareStickersStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private final ReporteContext context = new ReporteContext();

    public ReporteDTO generarReporte(String tipoReporte) {
        IReporteStrategy estrategia;

        switch (tipoReporte) {
            case "TOP_ALBUMS":
                estrategia = new ReporteTopAlbumsStrategy();
                break;
            case "RARE_STICKERS":
                estrategia = new ReporteRareStickersStrategy();
                break;
            default:
                throw new IllegalArgumentException("Tipo de reporte no reconocido: " + tipoReporte);
        }

        context.setStrategy(estrategia);
        String resultado = context.ejecutarReporte();

        Map<String, Object> datos = new HashMap();
        datos.put("tipoReporte", tipoReporte);
        datos.put("resultado", resultado);
        datos.put("detalle", "Datos simulados — implementación pendiente");

        return new ReporteDTO(datos, new Date());
    }
}

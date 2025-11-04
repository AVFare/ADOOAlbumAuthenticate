package com.adoo.album.service.impl;

import com.adoo.album.model.dto.ReporteDTO;
import com.adoo.album.service.strategy.IReporteStrategy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ReporteFactory {

    private final Map<String, IReporteStrategy> strategyMap;

    public ReporteFactory(List<IReporteStrategy> strategies) {
        strategyMap = strategies.stream()
        .collect(Collectors.toMap(IReporteStrategy::getTipoReporte, Function.identity()));
    }

    public IReporteStrategy getStrategy(String tipoReporte) {
        IReporteStrategy strategy = strategyMap.get(tipoReporte);

        if (strategy == null) {
            return new IReporteStrategy() {
                @Override
                public ReporteDTO generar() {
                    return new ReporteDTO(
                        tipoReporte,
                        "Tipo de reporte no reconocido.",
                        Collections.emptyList()
                    );
                }

                @Override
                public String getTipoReporte() {
                    return "ERROR_DEFAULT"; 
                }
            };
        }
        
        return strategy;
    }
}
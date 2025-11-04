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

    /**
     * Al iniciar, Spring inyecta una lista de TODOS los beans que 
     * implementan IReporteStrategy (gracias al @Component en cada uno).
     */
    public ReporteFactory(List<IReporteStrategy> strategies) {
        // Convertimos la lista en un Mapa para acceso O(1)
        // La clave es el String (ej: "TOP_ALBUMS")
        // El valor es la instancia de la estrategia (ej: TopAlbumsStrategy)
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(IReporteStrategy::getTipoReporte, Function.identity()));
    }

    /**
     * Busca y devuelve la estrategia correcta basada en el tipo.
     */
    public IReporteStrategy getStrategy(String tipoReporte) {
        // Obtenemos la estrategia del mapa
        IReporteStrategy strategy = strategyMap.get(tipoReporte);

        if (strategy == null) {
            
            // ----- INICIO DE LA CORRECCIÓN -----
            // No podemos usar una lambda porque IReporteStrategy no es funcional.
            // Usamos una clase anónima en su lugar.
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
                    return "ERROR_DEFAULT"; // Devuelve un tipo para el caso de error
                }
            };
            // ----- FIN DE LA CORRECCIÓN -----
        }
        
        return strategy;
    }
}
package com.adoo.album.service.strategy;

import com.adoo.album.infrastructure.persistence.repository.PackRepository;
import com.adoo.album.infrastructure.persistence.repository.UserRepository;
import com.adoo.album.model.dto.ReporteDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class PackOpenRateStrategy implements IReporteStrategy {

    private final PackRepository packRepository;
    private final UserRepository userRepository;

    public PackOpenRateStrategy(PackRepository packRepository, UserRepository userRepository) {
        this.packRepository = packRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public String getTipoReporte() {
        return "PACK_OPEN_RATE";
    }

    @Override
    public ReporteDTO generar() {
        long totalPacksComprados = packRepository.count();
        long totalCompradoresUnicos;
        
        try {
            totalCompradoresUnicos = packRepository.countDistinctUserIds(); 
        } catch (Exception e) {
            totalCompradoresUnicos = userRepository.count(); 
        }

        double tasaApertura = (totalCompradoresUnicos > 0) ? (double) totalPacksComprados / totalCompradoresUnicos : 0.0;

        List<Map<String, Object>> resultados = List.of(
            Map.of("Metrica", "Tasa Promedio de Apertura (Paquetes/Comprador)", "Valor", String.format("%.2f", tasaApertura))
        );

        return new ReporteDTO(
            getTipoReporte(),
            "Tasa promedio de paquetes comprados por cada usuario comprador.",
            resultados
        );
    }
}
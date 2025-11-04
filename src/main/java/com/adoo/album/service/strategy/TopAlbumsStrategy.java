package com.adoo.album.service.strategy;

import com.adoo.album.infrastructure.persistence.repository.UserRewardRepository;
import com.adoo.album.model.dto.ReporteDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TopAlbumsStrategy implements IReporteStrategy {

    private final UserRewardRepository userRewardRepository;

    public TopAlbumsStrategy(UserRewardRepository userRewardRepository) {
        this.userRewardRepository = userRewardRepository;
    }

    @Override
    public String getTipoReporte() {
        return "TOP_ALBUMS";
    }

    @Override
    public ReporteDTO generar() {
        List<Object[]> resultadosRaw = userRewardRepository.countCompletedAlbumsByAlbum();
        
        List<Map<String, Object>> resultadosMapeados = resultadosRaw.stream()
            .map(row -> {
                java.util.HashMap<String, Object> albumMap = new java.util.HashMap<>();
                albumMap.put("titulo", row[0]); 
                albumMap.put("completados", row[1]); 
                return (Map<String, Object>) albumMap;
            })
            .collect(Collectors.toList());

        return new ReporteDTO(
            getTipoReporte(),
            "Clasificación de álbumes según la cantidad de usuarios que han reclamado su premio (álbum completo).",
            resultadosMapeados
        );
    }
}
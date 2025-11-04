package com.adoo.album.service.strategy;

import com.adoo.album.infrastructure.persistence.repository.UserRepository;
import com.adoo.album.model.dto.ReporteDTO;
import org.springframework.stereotype.Component;
// ... (otros imports) ...
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserStatsStrategy implements IReporteStrategy {

    private final UserRepository userRepository;

    public UserStatsStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public String getTipoReporte() {
        return "USER_STATS";
    }

    @Override
    public ReporteDTO generar() {
        long totalUsers = userRepository.count();
        List<Object[]> rolesRaw = userRepository.countUsersByRole();

        List<Map<String, Object>> distribucionRoles = rolesRaw.stream()
            .map(row -> {
                java.util.HashMap<String, Object> roleMap = new java.util.HashMap<>();
                roleMap.put("rol", row[0].toString());
                roleMap.put("cantidad", row[1]);
                return roleMap;
            })
            .collect(Collectors.toList());

        List<Map<String, Object>> resultados = new ArrayList<>();
        resultados.add(Map.of("metricas", "Total de Usuarios Registrados", "valor", totalUsers));
        resultados.add(Map.of("metricas", "Distribución por Rol", "valor", distribucionRoles));

        return new ReporteDTO(
            getTipoReporte(),
            "Estadísticas generales sobre la base de usuarios (conteo total y distribución de roles).",
            resultados
        );
    }
}
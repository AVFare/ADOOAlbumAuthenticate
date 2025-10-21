package com.adoo.album.service.impl;

import com.adoo.album.model.entity.Sticker;
import com.adoo.album.model.entity.dto.ReporteDTO;
import com.adoo.album.repository.AlbumRepository;
import com.adoo.album.repository.StickerRepository;
import com.adoo.album.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private StickerRepository stickerRepository;

    @Override
    public ReporteDTO generarReporte(String tipoReporte) {
        return switch (tipoReporte) {
            case "TOP_ALBUMS" -> generarTopAlbumsCompletitud();
            case "RARE_STICKERS" -> generarFiguritasMasRaras();
            case "USER_STATS" -> generarEstadisticasUsuarios();
            // Implementación futura: case "PACK_OPEN_RATE" -> generarTasaAperturaPaquetes();
            default -> new ReporteDTO(tipoReporte, "Tipo de reporte no reconocido", Collections.emptyList());
        };
    }

    // Lógica para el reporte: Top Álbumes por Completitud
    private ReporteDTO generarTopAlbumsCompletitud() {
        // En una implementación real, usarías una consulta JPA o nativa
        // (Ej: Group By AlbumId, Count(distinct UserId) where estado = 'Reclamado')
        
        // Simulación de datos para demostración
        List<Map<String, Object>> resultadosSimulados = List.of(
            Map.of("album_id", 101L, "titulo", "Mundos Fantásticos Vol. 1", "completados", 54),
            Map.of("album_id", 105L, "titulo", "Leyendas del Deporte 2024", "completados", 48),
            Map.of("album_id", 103L, "titulo", "Viaje al Espacio", "completados", 35)
        );

        return new ReporteDTO(
            "TOP_ALBUMS",
            "Clasificación de álbumes según la cantidad de usuarios que han reclamado su premio (álbum completo).",
            resultadosSimulados
        );
    }

    // Lógica para el reporte: Figuritas más raras
    private ReporteDTO generarFiguritasMasRaras() {
        // En una implementación real:
        // 1. Buscar todas las stickers de rareza 'EPICA' o 'RARA'.
        // 2. Ordenarlas por 'stockDisponible' (ascendente) y 'rareza'.
        
        List<Sticker> stickersRaras = stickerRepository.findTop10ByOrderByRarezaDescStockDisponibleAsc();
        
       List<Map<String, Object>> resultados = stickersRaras.stream()
        .map(s -> {
            // FIX: Usar un HashMap mutable para resolver el problema de inferencia de tipo
            Map<String, Object> stickerMap = new java.util.HashMap<>();
            stickerMap.put("sticker_id", s.getId());
            stickerMap.put("nombre", s.getNombre());
            stickerMap.put("rareza", s.getRareza().name());
            stickerMap.put("stock_disponible", s.getStockDisponible());
            stickerMap.put("stock_total", s.getStockTotal());
            // Se asume que getAlbum() y getTitulo() ya existen en Album
            stickerMap.put("album_titulo", s.getAlbum().getTitulo()); 
            return stickerMap;
        })
        .collect(java.util.stream.Collectors.toList());

        return new ReporteDTO(
            "RARE_STICKERS",
            "Figuritas con menor stock disponible (las más difíciles de obtener).",
            resultados
        );
    }
    /**
     * Reporte que devuelve estadísticas básicas de la base de usuarios.
     * En un entorno real, el IUsuarioService necesitaría un método como findAll()
     * o countAll().
     */
    private ReporteDTO generarEstadisticasUsuarios() {
        
        // Simulación: Asumimos que el IUsuarioService tiene un método para contar
        // o que se usa directamente un repositorio para obtener los datos
        
        // Simulación de contar usuarios totales
        long totalUsers;
        try {
            // Asumimos que IUsuarioService tiene un método para contar
            // En realidad, tu IUsuarioDAO debería tener un .count() o .findAll()
            // Vamos a simular el conteo
            totalUsers = 1250; 
        } catch (Exception e) {
            totalUsers = 0;
        }

        // Simulación de la obtención de la distribución de roles
        // En una implementación real, sería una consulta GROUP BY (ej. en el DAO)
        List<Map<String, Object>> distribucionRoles = List.of(
            Map.of("rol", "USER", "cantidad", 1245L),
            Map.of("rol", "ADMIN", "cantidad", 5L)
        );

        List<Map<String, Object>> resultados = List.of(
            Map.of("metricas", "Total de Usuarios Registrados", "valor", totalUsers),
            Map.of("metricas", "Distribución por Rol", "valor", distribucionRoles)
        );


        return new ReporteDTO(
            "USER_STATS",
            "Estadísticas generales sobre la base de usuarios (conteo total y distribución de roles).",
            resultados
        );
    }
}
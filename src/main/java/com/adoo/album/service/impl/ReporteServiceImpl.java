package com.adoo.album.service.impl;

import com.adoo.album.model.entity.Pack;
import com.adoo.album.model.entity.Sticker;
import com.adoo.album.model.entity.dto.ReporteDTO;
import com.adoo.album.repository.AlbumRepository;
import com.adoo.album.repository.PackRepository;
import com.adoo.album.repository.StickerRepository;
import com.adoo.album.repository.UserRepository;
import com.adoo.album.repository.UserRewardRepository;
import com.adoo.album.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private UserRewardRepository userRewardRepository;

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReporteDTO generarReporte(String tipoReporte) {
        return switch (tipoReporte) {
            case "TOP_ALBUMS" -> generarTopAlbumsCompletitud();
            case "RARE_STICKERS" -> generarFiguritasMasRaras();
            case "USER_STATS" -> generarEstadisticasUsuarios();
            case "PACK_OPEN_RATE" -> generarTasaAperturaPaquetes();
            default -> new ReporteDTO(tipoReporte, "Tipo de reporte no reconocido", Collections.emptyList());
        };
    }

    // Lógica para el reporte: Top Álbumes por Completitud
    private ReporteDTO generarTopAlbumsCompletitud() {
        
        // 1. Ejecutar la consulta personalizada del repositorio.
        // El método devuelve List<Object[]> donde cada array es: [titulo_album (String), conteo (Long)]
        List<Object[]> resultadosRaw = userRewardRepository.countCompletedAlbumsByAlbum();
        
        // 2. Mapear los resultados crudos (Object[]) al formato List<Map<String, Object>>
        List<Map<String, Object>> resultadosMapeados = resultadosRaw.stream()
            .map(row -> {
                // Usamos HashMap para evitar problemas de casting/invalidez de Map.of en streams
                java.util.HashMap<String, Object> albumMap = new java.util.HashMap<>();
                
                // Los índices corresponden al orden del SELECT en la query:
                // SELECT ur.album.titulo (Index 0), COUNT(ur.id) (Index 1)
                albumMap.put("titulo", row[0]); 
                albumMap.put("completados", row[1]); 
                
                // Nota: El album_id no está disponible en la consulta actual.
                // Si necesitas el ID, modifica la consulta en UserRewardRepository a:
                // SELECT ur.album.id, ur.album.titulo, COUNT(ur.id) ...
                
                return (Map<String, Object>) albumMap;
            })
            .collect(Collectors.toList());

        return new ReporteDTO(
            "TOP_ALBUMS",
            "Clasificación de álbumes según la cantidad de usuarios que han reclamado su premio (álbum completo).",
            resultadosMapeados
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

    // Reporte que devuelve estadísticas básicas de la base de usuarios.
    private ReporteDTO generarEstadisticasUsuarios() {
    
    // 1. Obtener el total de usuarios registrados
    long totalUsers = userRepository.count();

    // 2. Obtener la distribución de usuarios por rol (consulta GROUP BY)
    List<Object[]> rolesRaw = userRepository.countUsersByRole();

    // 3. Mapear los resultados de roles a List<Map<String, Object>>
    List<Map<String, Object>> distribucionRoles = rolesRaw.stream()
        .map(row -> {
            java.util.HashMap<String, Object> roleMap = new java.util.HashMap<>();
            // row[0] es el Role (Enum), row[1] es el COUNT(Long)
            roleMap.put("rol", row[0].toString());
            roleMap.put("cantidad", row[1]);
            return roleMap;
        })
        .collect(java.util.stream.Collectors.toList());

    // 4. Estructurar el ReporteDTO final

    // Incluimos el total de usuarios como la primera métrica
    List<Map<String, Object>> resultados = new java.util.ArrayList<>();
    resultados.add(Map.of("metricas", "Total de Usuarios Registrados", "valor", totalUsers));
    
    // Y la distribución de roles como una métrica detallada
    resultados.add(Map.of("metricas", "Distribución por Rol", "valor", distribucionRoles));

    return new ReporteDTO(
        "USER_STATS",
        "Estadísticas generales sobre la base de usuarios (conteo total y distribución de roles).",
        resultados
    );
    }
    
    // Reporte: Tasa promedio de apertura de paquetes por usuario comprador
    private ReporteDTO generarTasaAperturaPaquetes() {
        
        // 1. Total de paquetes comprados (numerador)
        long totalPacksComprados = packRepository.count();

        // 2. Número de usuarios que han comprado AL MENOS un paquete (denominador)
        // Se asume que PackRepository tendrá un método para contar usuarios distintos
        // Si no lo tienes, puedes usar una consulta @Query
        
        // Simulación de la obtención de compradores únicos:
        long totalCompradoresUnicos;
        
        try {
            // Requiere un método personalizado en PackRepository
            totalCompradoresUnicos = packRepository.countDistinctUserIds(); 
        } catch (Exception e) {
            // Fallback si la consulta falla o si se usa el total de usuarios (menos preciso)
            totalCompradoresUnicos = userRepository.count(); 
        }


        double tasaApertura = 0.0;

        if (totalCompradoresUnicos > 0) {
            // Tasa = Paquetes Totales / Compradores Únicos
            tasaApertura = (double) totalPacksComprados / totalCompradoresUnicos;
        }

        List<Map<String, Object>> resultados = List.of(
            Map.of("Metrica", "Total de Paquetes Comprados", "Valor", totalPacksComprados),
            Map.of("Metrica", "Total de Usuarios Compradores Únicos", "Valor", totalCompradoresUnicos),
            Map.of("Metrica", "Tasa Promedio de Apertura (Paquetes/Comprador)", "Valor", String.format("%.2f", tasaApertura))
        );

        return new ReporteDTO(
            "PACK_OPEN_RATE",
            "Tasa promedio de paquetes comprados por cada usuario comprador.",
            resultados
        );
    }
}
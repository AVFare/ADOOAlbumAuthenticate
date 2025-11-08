package com.adoo.album.controller;

import com.adoo.album.model.dto.ReporteDTO;
import com.adoo.album.service.impl.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reportes", description = "Endpoints para generación de reportes estadísticos del sistema")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Operation(
            summary = "Obtener ranking de álbumes más completados",
            description = "Genera un reporte con los álbumes más completados por los usuarios.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado (solo ADMIN)", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/top-albums")
    public ResponseEntity<ReporteDTO> obtenerTopAlbums() {
        ReporteDTO reporte = reporteService.generarReporte("TOP_ALBUMS");
        return ResponseEntity.ok(reporte);
    }


    @Operation(
            summary = "Obtener figuritas más raras",
            description = "Genera un reporte con las figuritas menos comunes o más valiosas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado (solo ADMIN)", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rare-stickers")
    public ResponseEntity<ReporteDTO> obtenerFiguritasRaras() {
        ReporteDTO reporte = reporteService.generarReporte("RARE_STICKERS");
        return ResponseEntity.ok(reporte);
    }

    @Operation(
            summary = "Obtener tasa de apertura de paquetes",
            description = "Devuelve un reporte con la tasa de apertura de paquetes por usuario.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/pack-open-rate")
    public ResponseEntity<ReporteDTO> obtenerTasaApertura() {
        ReporteDTO reporte = reporteService.generarReporte("PACK_OPEN_RATE");
        return ResponseEntity.ok(reporte);
    }

    @Operation(
            summary = "Obtener estadísticas de usuarios",
            description = "Genera un reporte con información agregada de los usuarios del sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado (solo ADMIN)", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user-stats")
    public ResponseEntity<ReporteDTO> obtenerEstadisticasUsuarios() {
        ReporteDTO reporte = reporteService.generarReporte("USER_STATS");
        return ResponseEntity.ok(reporte);
    }
}
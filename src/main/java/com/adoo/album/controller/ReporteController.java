package com.adoo.album.controller;

import com.adoo.album.model.entity.dto.ReporteDTO;
import com.adoo.album.service.impl.ReporteServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReporteController {

    @Autowired
    private ReporteServiceImpl reporteService;

    // Sólo para el rol ADMIN
    // GET /reports/top-albums
    @GetMapping("/top-albums")
    public ResponseEntity<ReporteDTO> obtenerTopAlbums() {
        ReporteDTO reporte = reporteService.generarReporte("TOP_ALBUMS");
        return ResponseEntity.ok(reporte);
    }

    // Sólo para el rol ADMIN
    // GET /reports/rare-stickers
    @GetMapping("/rare-stickers")
    public ResponseEntity<ReporteDTO> obtenerFiguritasRaras() {
        ReporteDTO reporte = reporteService.generarReporte("RARE_STICKERS");
        return ResponseEntity.ok(reporte);
    }
    
    // Endpoint Adicional para demostrar el patrón
    // GET /reports/pack-open-rate
    @GetMapping("/pack-open-rate")
    public ResponseEntity<ReporteDTO> obtenerTasaApertura() {
        ReporteDTO reporte = reporteService.generarReporte("PACK_OPEN_RATE");
        return ResponseEntity.ok(reporte);
    }

    // Sólo para el rol ADMIN
    // GET /reports/user-stats
    @GetMapping("/user-stats")
    public ResponseEntity<ReporteDTO> obtenerEstadisticasUsuarios() {
        ReporteDTO reporte = reporteService.generarReporte("USER_STATS");
        return ResponseEntity.ok(reporte);
    }
}
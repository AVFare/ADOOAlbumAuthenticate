package com.adoo.album.controller;

import com.adoo.album.model.entity.dto.ReporteDTO;
import com.adoo.album.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/top-albums")
    public ReporteDTO obtenerTopAlbums() {
        return reporteService.generarReporte("TOP_ALBUMS");
    }

    @GetMapping("/rare-stickers")
    public ReporteDTO obtenerFiguritasRaras() {
        return reporteService.generarReporte("RARE_STICKERS");
    }
}

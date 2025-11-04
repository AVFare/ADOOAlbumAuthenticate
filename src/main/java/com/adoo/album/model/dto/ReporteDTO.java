package com.adoo.album.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * DTO genérico para reportes.
 * Permite devolver una estructura flexible (Lista de Mapas) para los datos del reporte.
 */
public class ReporteDTO {
    private String tipoReporte;
    private LocalDateTime fechaGeneracion;
    private String descripcion;
    
    // Contiene la lista de resultados. Cada Map<String, Object> es una fila.
    private List<Map<String, Object>> resultados;

    // Constructor
    public ReporteDTO(String tipoReporte, String descripcion, List<Map<String, Object>> resultados) {
        this.tipoReporte = tipoReporte;
        this.descripcion = descripcion;
        this.resultados = resultados;
        this.fechaGeneracion = LocalDateTime.now();
    }

    // Getters y Setters (Omitidos para brevedad)

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Map<String, Object>> getResultados() {
        return resultados;
    }

    public void setResultados(List<Map<String, Object>> resultados) {
        this.resultados = resultados;
    }
}
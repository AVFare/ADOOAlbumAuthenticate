package com.adoo.album.model.entity.dto;

import java.util.Date;
import java.util.Map;

public class ReporteDTO {

    private Map<String, Object> datos;
    private Date fechaGeneracion;

    public ReporteDTO() {
    }

    public ReporteDTO(Map<String, Object> datos, Date fechaGeneracion) {
        this.datos = datos;
        this.fechaGeneracion = fechaGeneracion;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, Object> datos) {
        this.datos = datos;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
}


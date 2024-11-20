package com.example.ayuda_municipios.solicitudes.dto;

import com.example.ayuda_municipios.solicitudes.Solicitud;

import lombok.Data;

@Data
public class RespuestaSolicitudDTO {
    private Solicitud solicitud;

    public RespuestaSolicitudDTO(Solicitud solicitud) {

        this.solicitud = new Solicitud(
                solicitud.getId(), solicitud.getFecha_publicacion(), 
                solicitud.getAyuda(),solicitud.getMunicipio(), 
                solicitud.getCreador(), solicitud.getCalle(),
                solicitud.isCompletado(),solicitud.getPrioridad());
    }
}

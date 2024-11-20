package com.example.ayuda_municipios.solicitudes.dto;

import java.util.List;

import com.example.ayuda_municipios.solicitudes.Solicitud;

import lombok.Data;

@Data
public class RespuestaSolicitudesDTO {
    private List<Solicitud> solicitudes;

    public RespuestaSolicitudesDTO(List<Solicitud> solicitudes) {

        this.solicitudes = solicitudes.stream().map(solicitud -> {
            return new Solicitud(solicitud.getId(), solicitud.getFecha_publicacion(), 
            solicitud.getAyuda(),solicitud.getMunicipio(), 
            solicitud.getCreador(), solicitud.getCalle(),
            solicitud.isCompletado(),solicitud.getPrioridad());
        }).toList();
    }
}

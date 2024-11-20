package com.example.ayuda_municipios.municipios.dto;

import java.util.List;

import com.example.ayuda_municipios.municipios.Municipio;

import lombok.Data;

@Data
public class RespuestaMunicipiosDTO {
    private List<Municipio> municipios;

     public RespuestaMunicipiosDTO(List<Municipio> municipios) {

        this.municipios = municipios.stream().map(municipio -> {
            return new Municipio(municipio.getId(), municipio.getNombre(),municipio.getCp(),municipio.getProvincia(), null);
        }).toList();
    }
}

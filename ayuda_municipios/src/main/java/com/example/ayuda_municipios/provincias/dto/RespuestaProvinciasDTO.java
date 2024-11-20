package com.example.ayuda_municipios.provincias.dto;

import java.util.List;

import com.example.ayuda_municipios.provincias.Provincia;

import lombok.Data;

@Data
public class RespuestaProvinciasDTO {
    private List<Provincia> provincias;

    public RespuestaProvinciasDTO(List<Provincia> provincias) {

        this.provincias = provincias.stream().map(provincia -> {
            return new Provincia(provincia.getId(), provincia.getNombre(), null);
        }).toList();
    }
}

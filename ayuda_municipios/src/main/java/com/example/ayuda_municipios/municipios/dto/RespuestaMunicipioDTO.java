package com.example.ayuda_municipios.municipios.dto;

import com.example.ayuda_municipios.municipios.Municipio;

import lombok.Data;

@Data
public class RespuestaMunicipioDTO {
     private Municipio municipio;
     
         public RespuestaMunicipioDTO(Municipio municipio) {
             this.municipio = new Municipio(municipio.getId(), municipio.getNombre(),municipio.getCp(),municipio.getProvincia(), null);
    }
}

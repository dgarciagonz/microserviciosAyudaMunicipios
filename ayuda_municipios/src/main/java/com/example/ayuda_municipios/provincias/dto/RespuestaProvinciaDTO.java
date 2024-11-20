package com.example.ayuda_municipios.provincias.dto;

import com.example.ayuda_municipios.provincias.Provincia;

import lombok.Data;

@Data
public class RespuestaProvinciaDTO {
     private Provincia provincia;
     
         public RespuestaProvinciaDTO(Provincia provincia) {

             this.provincia = new Provincia(provincia.getId(),provincia.getNombre(),null);
    }
}


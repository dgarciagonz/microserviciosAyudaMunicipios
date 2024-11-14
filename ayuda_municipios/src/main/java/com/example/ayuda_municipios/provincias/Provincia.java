package com.example.ayuda_municipios.provincias;

import java.util.List;

import com.example.ayuda_municipios.municipios.Municipio;
import com.example.ayuda_municipios.provincias.dto.ProvinciaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Municipio> municipios;
    

    public static Provincia fromDTO(ProvinciaDTO provinciaDTO) {
        return new Provincia(0, provinciaDTO.getNombre(),null);
    }
}

package com.example.ayuda_municipios.municipios;

import java.util.List;

import com.example.ayuda_municipios.municipios.dto.MunicipioDTO;
import com.example.ayuda_municipios.solicitudes.Solicitud;

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
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solicitud> solicitudes;

    static Municipio fromDTO(MunicipioDTO municipioDTO) {
        return new Municipio(0, municipioDTO.getNombre(),null);
    }

}

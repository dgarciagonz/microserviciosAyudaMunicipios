package com.example.ayuda_municipios.solicitudes;

import com.example.ayuda_municipios.municipios.Municipio;
import com.example.ayuda_municipios.solicitudes.dto.SolicitudDTO;
import com.example.ayuda_municipios.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fecha_publicacion;
    private String ayuda;

    @ManyToOne
    @JoinColumn(name = "municipio")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "creador")
    Usuario creador;

    private String calle;
    private boolean completado;
    private String prioridad;

    static Solicitud fromDTO(SolicitudDTO solicitudDTO, Municipio municipio, Usuario usuario) {
        return new Solicitud(0,
                solicitudDTO.getFecha_publicacion(),
                solicitudDTO.getAyuda(),
                municipio,
                usuario,
                solicitudDTO.getCalle(),
                solicitudDTO.isCompletado(),
                solicitudDTO.getPrioridad());
    }
}

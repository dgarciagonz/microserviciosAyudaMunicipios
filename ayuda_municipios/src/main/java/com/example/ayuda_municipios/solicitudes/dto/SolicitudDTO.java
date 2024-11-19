package com.example.ayuda_municipios.solicitudes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class SolicitudDTO {
    @NotNull
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}", message = "La fecha no tiene un formato correcto")
    private String fecha_publicacion;
    @NotBlank(message = "el campo Ayuda no puede estar vacío")
    @Size(min = 3, message = "el campo Ayuda debe tener al menos 3 caracteres")
    private String ayuda;
    @NotNull(message = "el municipio no puede estar en blanco")
    @Positive(message = "el municipio debe ser positivo")
    private int municipio;
    @NotNull(message = "el creador no puede estar en blanco")
    private int creador;
    @Size(min = 3,max = 255, message = "el campo calle debe tener al menos 3 caracteres")
    private String calle;
    private boolean completado = false;
    @Pattern(regexp = "^(ALTA|MEDIA|BAJA)$", message = "La prioridad debe ser ALTA, MEDIA o BAJA")
    private String prioridad;
}

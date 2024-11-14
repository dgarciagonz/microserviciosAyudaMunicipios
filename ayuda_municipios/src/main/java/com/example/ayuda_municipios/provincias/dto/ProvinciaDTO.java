package com.example.ayuda_municipios.provincias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class ProvinciaDTO {
    @NotBlank(message = "La provincia no puede estar vacía")
    @Size(min = 2, message = "La provincia debe tener al menos 2 caracteres")
    private String nombre;
}

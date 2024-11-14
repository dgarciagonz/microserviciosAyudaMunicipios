package com.example.ayuda_municipios.municipios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class MunicipioDTO {
    @NotBlank(message = "El municipio no puede estar vacío")
    @Size(min = 2, message = "El municipio debe tener al menos 2 caracteres")
    private String nombre;
    @Positive (message = "El código postal debe ser válido")
    private int cp;
    @Min(1)
    @Positive (message = "La provincia debe ser válida")
    private int provincia;
}


package com.example.ayuda_municipios.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}

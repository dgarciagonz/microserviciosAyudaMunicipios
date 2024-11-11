package com.example.ayuda_municipios.usuarios;

import com.example.ayuda_municipios.usuarios.dto.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    private String rol;

     static Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return new Usuario(0, usuarioDTO.getUsername(),usuarioDTO.getEmail(),usuarioDTO.getPassword(),"USER");
    }
}

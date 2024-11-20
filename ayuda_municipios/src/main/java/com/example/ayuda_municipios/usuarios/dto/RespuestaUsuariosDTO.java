package com.example.ayuda_municipios.usuarios.dto;

import java.util.List;

import com.example.ayuda_municipios.usuarios.Usuario;

import lombok.Data;

@Data
public class RespuestaUsuariosDTO {
    private List<Usuario> usuarios;

     public RespuestaUsuariosDTO(List<Usuario> usuarios) {

        this.usuarios = usuarios.stream().map(usuario -> {
            return new Usuario(usuario.getId(), usuario.getUsername(),null,null, usuario.getRol(), null);
        }).toList();
    }
}

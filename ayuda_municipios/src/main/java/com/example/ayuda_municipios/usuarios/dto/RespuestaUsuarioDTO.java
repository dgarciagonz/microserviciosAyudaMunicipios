package com.example.ayuda_municipios.usuarios.dto;

import com.example.ayuda_municipios.usuarios.Usuario;

import lombok.Data;

@Data
public class RespuestaUsuarioDTO {
    private Usuario usuario;

    public RespuestaUsuarioDTO(Usuario usuario) {
        this.usuario = new Usuario(usuario.getId(), usuario.getUsername(),null,null, usuario.getRol(), null);
    }
}

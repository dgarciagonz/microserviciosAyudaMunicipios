package com.example.ayuda_municipios.usuarios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ayuda_municipios.usuarios.dto.UsuarioDTO;
import com.example.ayuda_municipios.usuarios.dto.RespuestaUsuarioDTO;
import com.example.ayuda_municipios.usuarios.dto.RespuestaUsuariosDTO;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuariosService usuariosService;

    @GetMapping()
    public RespuestaUsuariosDTO getAll() {
        return new RespuestaUsuariosDTO(usuariosService.getAll());
    }

    @GetMapping("/{id}")
    public RespuestaUsuarioDTO buscarPorId(@PathVariable int id) {
        return new RespuestaUsuarioDTO(usuariosService.getById(id));
        
    }

    @PutMapping("/{id}")
    public RespuestaUsuarioDTO updateUsuario(@PathVariable int id, @RequestBody @Valid UsuarioDTO u) {
        return new RespuestaUsuarioDTO(usuariosService.update(id, u));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarUsuario(@PathVariable int id) {
        usuariosService.delete(id);
    }
}

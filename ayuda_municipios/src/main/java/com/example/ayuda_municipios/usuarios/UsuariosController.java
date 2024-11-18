package com.example.ayuda_municipios.usuarios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.usuarios.dto.UsuarioDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuariosService usuariosService;

    @GetMapping()
    public List<Usuario> getAll() {
        return usuariosService.getAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable int id) {
        Usuario usuario = usuariosService.getById(id);
        if (usuario != null) {
            return usuario;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Usuario no encontrado", null);
        }
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable int id, @RequestBody @Valid UsuarioDTO u) {
        return usuariosService.update(id, u);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarUsuario(@PathVariable int id) {
        usuariosService.delete(id);
    }
}

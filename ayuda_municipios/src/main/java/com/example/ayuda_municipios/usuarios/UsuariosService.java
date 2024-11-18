package com.example.ayuda_municipios.usuarios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.auth.dto.LoginDTO;
import com.example.ayuda_municipios.solicitudes.Solicitud;
import com.example.ayuda_municipios.solicitudes.SolicitudesRepository;
import com.example.ayuda_municipios.usuarios.dto.UsuarioDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    private final UsuariosRepository usuariosRepository;
    private final SolicitudesRepository solicitudesRepository;

    List<Usuario> getAll() {
        return usuariosRepository.findBy();
    }

    public Usuario getById(int id) {
        Usuario u = usuariosRepository.findUsuarioById(id);
        return u;
    }

    public Usuario insert(UsuarioDTO usuarioDTO) throws NoSuchAlgorithmException {
        usuarioDTO.setPassword(encodePassword(usuarioDTO.getPassword()));
        Usuario usuario = usuariosRepository.save(Usuario.fromDTO(usuarioDTO));
        return usuariosRepository.findUsuarioById(usuario.getId());
    }

    public Usuario login(LoginDTO loginDTO) throws NoSuchAlgorithmException {
        return usuariosRepository.findByCorreoAndPassword(loginDTO.getCorreo(), encodePassword(loginDTO.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));
    }

    public List<Solicitud> getSolicitudesCreadas(int idUsuario) {
        Usuario usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return solicitudesRepository.findByAutor(usuario);
    }

    public Usuario update(int id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Usuario usuarioUpdate = Usuario.fromDTO(usuarioDTO);
        usuarioUpdate.setId(id);
        usuarioUpdate.setPassword(usuario.getPassword());
        usuariosRepository.save(usuarioUpdate);
        return usuariosRepository.findUsuarioById(usuario.getId());
    }

    public void delete(int idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }

    private String encodePassword(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        String encodedPass = Base64.getEncoder().encodeToString(hash);
        return encodedPass;
    }
}

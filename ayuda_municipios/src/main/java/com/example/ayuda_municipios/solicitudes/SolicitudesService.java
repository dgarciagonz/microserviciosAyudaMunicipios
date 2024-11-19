package com.example.ayuda_municipios.solicitudes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.core.Authentication;

import com.example.ayuda_municipios.municipios.Municipio;
import com.example.ayuda_municipios.municipios.MunicipiosRepository;
import com.example.ayuda_municipios.solicitudes.dto.SolicitudDTO;
import com.example.ayuda_municipios.usuarios.Usuario;
import com.example.ayuda_municipios.usuarios.UsuariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolicitudesService {
    private final SolicitudesRepository solicitudesRepository;
    private final MunicipiosRepository municipiosRepository;
    private final UsuariosRepository usuariosRepository;

    public List<Solicitud> SolicitudesCompletadas() {
        return solicitudesRepository.findSolicitudesCompletadas();
    }

    public List<Solicitud> SolicitudesNoCompletadas() {
        return solicitudesRepository.findSolicitudesNoCompletadas();
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudesRepository.findBy();
    }

    public Solicitud getById(int id) {
        Solicitud s = solicitudesRepository.findById(id);
        return s;
    }

    public List<Solicitud> findByMunicipio(int municipioId) {
        Municipio municipio = municipiosRepository.findById(municipioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado"));

        return solicitudesRepository.findByMunicipio(municipio);

    }

    public Solicitud insert(SolicitudDTO solicitudDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        Usuario usuario = usuariosRepository.findUsuarioById(idAuth);

        Municipio municipio = municipiosRepository.findById(solicitudDTO.getMunicipio())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado"));

        Solicitud solicitud = solicitudesRepository.save(Solicitud.fromDTO(solicitudDTO, municipio, usuario));
        return solicitudesRepository.findById(solicitud.getId());
    }

    public Solicitud update(int id, SolicitudDTO solicitudDTO) {
        Solicitud solicitud = solicitudesRepository.findById(id);

        Municipio municipio = municipiosRepository.findById(solicitudDTO.getMunicipio())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado"));

        Usuario usuario = usuariosRepository.findById(solicitudDTO.getCreador())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
                Usuario usuarioActual= usuariosRepository.findUsuarioById(idAuth);

        if (usuario.getId() != usuarioActual.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para modificar esta solicitud");
        }

        solicitud = Solicitud.fromDTO(solicitudDTO, municipio, usuario);
        solicitud.setId(id);
        solicitudesRepository.save(solicitud);
        return solicitudesRepository.findById(solicitud.getId());
    }

    public Solicitud completar(int id) {
        Solicitud solicitud = solicitudesRepository.findById(id);
        Usuario usuario=solicitud.getCreador();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        Usuario usuarioActual= usuariosRepository.findUsuarioById(idAuth);

       if (usuario.getId() != usuarioActual.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para modificar esta solicitud");
        }
        solicitud.setCompletado(true);
        solicitudesRepository.save(solicitud);
        return solicitudesRepository.findById(solicitud.getId());
    }

    public void delete(int id) {
        solicitudesRepository.deleteById(id);
    }
}

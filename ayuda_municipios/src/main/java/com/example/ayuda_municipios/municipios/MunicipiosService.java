package com.example.ayuda_municipios.municipios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.municipios.dto.MunicipioDTO;
import com.example.ayuda_municipios.provincias.Provincia;
import com.example.ayuda_municipios.provincias.ProvinciasRepository;
import com.example.ayuda_municipios.usuarios.Usuario;
import com.example.ayuda_municipios.usuarios.UsuariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MunicipiosService {
    private final MunicipiosRepository municipiosRespository;
    private final ProvinciasRepository provinciasRespository;
    private final UsuariosRepository usuariosRepository;


    public List<Municipio> verMunicipios() {
        return municipiosRespository.findBy();
    }

    public Municipio getById(int id) {
        Municipio m = municipiosRespository.findMunicipioById(id);
        return m;
    }

    public Municipio insert(MunicipioDTO municipioDTO) {
        Provincia provincia = provinciasRespository.findById(municipioDTO.getProvincia())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provincia no encontrada"));

        Municipio municipio = municipiosRespository.save(Municipio.fromDTO(municipioDTO, provincia));
        return municipiosRespository.findMunicipioById(municipio.getId());
    }

    public Municipio update(int id, MunicipioDTO municipioDTO) {
        if (!municipiosRespository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado");
        }

        Provincia provincia = provinciasRespository.findById(municipioDTO.getProvincia())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provincia no encontrada"));

        Municipio municipio = Municipio.fromDTO(municipioDTO, provincia);
        municipio.setId(id);
        municipiosRespository.save(municipio);
        return municipiosRespository.findMunicipioById(municipio.getId());
    }

    public void delete(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        Usuario usuarioActual= usuariosRepository.findUsuarioById(idAuth);

        if (usuarioActual.getRol()=="ADMIN") {
            municipiosRespository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo los administradores tienen permisos para eliminar");
        }

    }
}

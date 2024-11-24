package com.example.ayuda_municipios.provincias;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.provincias.dto.ProvinciaDTO;
import com.example.ayuda_municipios.usuarios.Usuario;
import com.example.ayuda_municipios.usuarios.UsuariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinciasService {
    private final ProvinciasRepository provinciasRepository;
        private final UsuariosRepository usuariosRepository;


    public List<Provincia> verProvincias(){
        return provinciasRepository.findBy();
    }

    public Provincia getById(int id) {
        Provincia p = provinciasRepository.findProvinciaById(id);
        return p;
    }

    public Provincia getByNombre(String nombre) {
        Provincia p = provinciasRepository.findProvinciaByNombre(nombre);
        return p;
    }

    public Provincia insert(ProvinciaDTO provinciaDTO) {
        Provincia provincia = provinciasRepository.save(Provincia.fromDTO(provinciaDTO));
        return provinciasRepository.findProvinciaById(provincia.getId());
    }

    public Provincia update(int id, ProvinciaDTO provinciaDTO) {
        if (!provinciasRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provincia no encontrado");
        }
        Provincia provincia = Provincia.fromDTO(provinciaDTO); 
        provincia.setId(id);
        provinciasRepository.save(provincia);
        return provinciasRepository.findProvinciaById(provincia.getId());
    }

    public void delete(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        Usuario usuarioActual= usuariosRepository.findUsuarioById(idAuth);

        if (usuarioActual.getRol().equals("ADMIN")) {
            provinciasRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo los administradores tienen permisos para eliminar");
        }
       
    }
}

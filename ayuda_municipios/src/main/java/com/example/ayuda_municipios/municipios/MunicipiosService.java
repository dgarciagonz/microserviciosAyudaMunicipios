package com.example.ayuda_municipios.municipios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.municipios.dto.MunicipioDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MunicipiosService {
    private final MunicipiosRepository municipiosRespository;

    public List<Municipio> getAll() {
        return municipiosRespository.findBy();
    }

    public Municipio getById(int id) {
        Municipio m = municipiosRespository.findMunicipioById(id);
        return m;
    }

    public Municipio insert(MunicipioDTO municipioDTO) {
        Municipio municipio = municipiosRespository.save(Municipio.fromDTO(municipioDTO));
        return municipiosRespository.findMunicipioById(municipio.getId());
    }

    public Municipio update(int id, MunicipioDTO municipioDTO) {
        if (!municipiosRespository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado");
        }

        Municipio municipio = Municipio.fromDTO(municipioDTO); 
        municipio.setId(id);
        municipiosRespository.save(municipio);
        return municipiosRespository.findMunicipioById(municipio.getId());
    }

    public void delete(int id) {
        municipiosRespository.deleteById(id);
    }
}

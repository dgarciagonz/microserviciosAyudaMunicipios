package com.example.ayuda_municipios.provincias;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.provincias.dto.ProvinciaDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinciasService {
    private final ProvinciasRepository provinciasRepository;

    public List<Provincia> findAllOrdenado(){
        return provinciasRepository.findAll(Sort.by(Sort.Order.asc("nombre")));
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
        provinciasRepository.deleteById(id);
    }
}

package com.example.ayuda_municipios.municipios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.municipios.dto.MunicipioDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/municipios")
@RequiredArgsConstructor
public class MunicipiosController {
    private final MunicipiosService municipiosService;

    @GetMapping()
    public List<Municipio> getAll() {
        return municipiosService.verMunicipios();
    }

    @GetMapping("/{id}")
    public Municipio buscarPorId(@PathVariable int id) {
        Municipio municipio = municipiosService.getById(id);
        if (municipio != null) {
            return municipio;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Municipio no encontrado", null);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Municipio nuevoMunicipio(@RequestBody @Valid MunicipioDTO municipioDTO) {
        return municipiosService.insert(municipioDTO);
    }

    @PutMapping("/{id}")
    public Municipio updateMunicipio(@PathVariable int id, @RequestBody @Valid MunicipioDTO municipioDTO) {
        return municipiosService.update(id, municipioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarMunicipio(@PathVariable int id) {
        municipiosService.delete(id);
    }
}

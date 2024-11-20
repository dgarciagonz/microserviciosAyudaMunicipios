package com.example.ayuda_municipios.municipios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ayuda_municipios.municipios.dto.MunicipioDTO;
import com.example.ayuda_municipios.municipios.dto.RespuestaMunicipioDTO;
import com.example.ayuda_municipios.municipios.dto.RespuestaMunicipiosDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/municipios")
@RequiredArgsConstructor
public class MunicipiosController {
    private final MunicipiosService municipiosService;

    @GetMapping()
    public RespuestaMunicipiosDTO getAll() {
        return new RespuestaMunicipiosDTO(municipiosService.verMunicipios());
    }

    @GetMapping("/{id}")
    public RespuestaMunicipioDTO buscarPorId(@PathVariable int id) {
        return new RespuestaMunicipioDTO(municipiosService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaMunicipioDTO nuevoMunicipio(@RequestBody @Valid MunicipioDTO municipioDTO) {
        return new RespuestaMunicipioDTO(municipiosService.insert(municipioDTO));
    }

    @PutMapping("/{id}")
    public RespuestaMunicipioDTO updateMunicipio(@PathVariable int id, @RequestBody @Valid MunicipioDTO municipioDTO) {
        return new RespuestaMunicipioDTO(municipiosService.update(id, municipioDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarMunicipio(@PathVariable int id) {
        municipiosService.delete(id);
    }
}

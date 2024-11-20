package com.example.ayuda_municipios.provincias;

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

import com.example.ayuda_municipios.provincias.dto.ProvinciaDTO;
import com.example.ayuda_municipios.provincias.dto.RespuestaProvinciaDTO;
import com.example.ayuda_municipios.provincias.dto.RespuestaProvinciasDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/provincias")
@RequiredArgsConstructor
public class ProvinciasController {
    private final ProvinciasService provinciasService;

    @GetMapping()
    public RespuestaProvinciasDTO obtenerProvincias() {
        return new RespuestaProvinciasDTO(provinciasService.verProvincias());
    }

    @GetMapping("/{id}")
    public RespuestaProvinciaDTO buscarPorId(@PathVariable int id) {
        return new RespuestaProvinciaDTO(provinciasService.getById(id));
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaProvinciaDTO nuevoProvincia(@RequestBody @Valid ProvinciaDTO provinciaDTO) {        
        return new RespuestaProvinciaDTO(provinciasService.insert(provinciaDTO));
    }

    @PutMapping("/{id}")
    public RespuestaProvinciaDTO updateProvincia(@PathVariable int id, @RequestBody @Valid ProvinciaDTO provinciaDTO) {
        return new RespuestaProvinciaDTO(provinciasService.update(id, provinciaDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarProvincia(@PathVariable int id) {
        provinciasService.delete(id);
    }
}

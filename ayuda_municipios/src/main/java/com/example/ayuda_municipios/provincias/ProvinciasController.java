package com.example.ayuda_municipios.provincias;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.example.ayuda_municipios.provincias.dto.ProvinciaDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/provincias")
@RequiredArgsConstructor
public class ProvinciasController {
    private final ProvinciasService provinciasService;

    @GetMapping()
    public List<Provincia> obtenerProvincias() {
        return provinciasService.findAllOrdenado();
    }

    @GetMapping("/{id}")
    public Provincia buscarPorId(@PathVariable int id) {
        Provincia provincia = provinciasService.getById(id);
        if(provincia != null) {
            return provincia;
        } else {
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Provincia no encontrado", null);
        }
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Provincia nuevoProvincia(@RequestBody @Valid ProvinciaDTO p) {        
        return provinciasService.insert(p);
    }

    @PutMapping("/{id}")
    public Provincia updateProvincia(@PathVariable int id, @RequestBody @Valid ProvinciaDTO m) {
        return provinciasService.update(id, m);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarProvincia(@PathVariable int id) {
        provinciasService.delete(id);
    }
}

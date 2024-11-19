package com.example.ayuda_municipios.solicitudes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ayuda_municipios.solicitudes.dto.SolicitudDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudesController {
    private final SolicitudesService solicitudesService;

    @GetMapping()
    public List<Solicitud> solicitudesInompletas() {
        return solicitudesService.SolicitudesNoCompletadas();
    }

    @GetMapping("/completadas")
    public List<Solicitud> solicitudesCompletas(){
        return solicitudesService.SolicitudesCompletadas();
    }

    @GetMapping("/todas")
    public List<Solicitud> todas(){
        return solicitudesService.getSolicitudes();
    }

    @GetMapping("/{id}")
    public Solicitud verSolicitud(@PathVariable int id){
        return solicitudesService.getById(id);
    }

    @GetMapping("/municipio/{id}")
    public List<Solicitud> getSolicitudesByMunicipio(@PathVariable int id) {
        return solicitudesService.findByMunicipio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Solicitud nuevaSolicitud(@RequestBody @Valid SolicitudDTO solicitudDTO) {
        return solicitudesService.insert(solicitudDTO);
    }

    @PutMapping("/{id}")
    public Solicitud updateSolicitud(@PathVariable int id, @RequestBody @Valid SolicitudDTO solicitudDTO) {
        return solicitudesService.update(id, solicitudDTO);
    }

    @PatchMapping("{id}/completar")
    public Solicitud completarSolicitud(@PathVariable int id) {
        return solicitudesService.completar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarSolicitud(@PathVariable int id) {
        solicitudesService.delete(id);
    }
}

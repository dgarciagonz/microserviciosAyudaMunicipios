package com.example.ayuda_municipios.solicitudes;

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

import com.example.ayuda_municipios.solicitudes.dto.RespuestaSolicitudDTO;
import com.example.ayuda_municipios.solicitudes.dto.RespuestaSolicitudesDTO;
import com.example.ayuda_municipios.solicitudes.dto.SolicitudDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudesController {
    private final SolicitudesService solicitudesService;

    @GetMapping()
    public RespuestaSolicitudesDTO solicitudesInompletas() {
        return new RespuestaSolicitudesDTO(solicitudesService.SolicitudesNoCompletadas());
    }

    @GetMapping("/completadas")
    public RespuestaSolicitudesDTO solicitudesCompletas(){
        return new RespuestaSolicitudesDTO(solicitudesService.SolicitudesCompletadas());
    }

    @GetMapping("/todas")
    public RespuestaSolicitudesDTO todas(){
        return new RespuestaSolicitudesDTO(solicitudesService.getSolicitudes());
    }

    @GetMapping("/municipio/{id}")
    public RespuestaSolicitudesDTO getSolicitudesByMunicipio(@PathVariable int id) {
        return new RespuestaSolicitudesDTO(solicitudesService.findByMunicipio(id));
    }

    @GetMapping("/usuario/{id}")
    public RespuestaSolicitudesDTO getSolicitudesByUsuario(@PathVariable int id) {
        return new RespuestaSolicitudesDTO(solicitudesService.findByMunicipio(id));
    }
    @GetMapping("/{id}")
    public RespuestaSolicitudDTO verSolicitud(@PathVariable int id){
        return new RespuestaSolicitudDTO(solicitudesService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaSolicitudDTO nuevaSolicitud(@RequestBody @Valid SolicitudDTO solicitudDTO) {
        return new RespuestaSolicitudDTO(solicitudesService.insert(solicitudDTO));
    }

    @PutMapping("/{id}")
    public RespuestaSolicitudDTO updateSolicitud(@PathVariable int id, @RequestBody @Valid SolicitudDTO solicitudDTO) {
        return new RespuestaSolicitudDTO(solicitudesService.update(id, solicitudDTO));
    }

    @PatchMapping("/completar/{id}")
    public RespuestaSolicitudDTO completarSolicitud(@PathVariable int id) {
        return new RespuestaSolicitudDTO(solicitudesService.completar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarSolicitud(@PathVariable int id) {
        solicitudesService.delete(id);
    }
}

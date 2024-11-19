package com.example.ayuda_municipios.solicitudes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ayuda_municipios.municipios.Municipio;
import com.example.ayuda_municipios.usuarios.Usuario;


@Repository
public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {
    
    @Query("SELECT s FROM Solicitud s WHERE s.completado = false")
    List<Solicitud> findSolicitudesNoCompletadas();

    @Query("SELECT s FROM Solicitud s WHERE s.completado = true")
    List<Solicitud> findSolicitudesCompletadas();

    List<Solicitud> findBy();

    Solicitud findById(int id);

    List<Solicitud> findByMunicipio(Municipio municipio);
    List<Solicitud> findByCreador(Usuario usuario);

}

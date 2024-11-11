package com.example.ayuda_municipios.municipios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipio, Integer> {
    List<Municipio> findBy();

    Municipio findMunicipioById(int id);

    Municipio findMunicipioByNombre(String nombre);

}

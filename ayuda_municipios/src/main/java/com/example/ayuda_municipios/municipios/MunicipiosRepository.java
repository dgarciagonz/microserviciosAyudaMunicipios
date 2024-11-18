package com.example.ayuda_municipios.municipios;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ayuda_municipios.provincias.Provincia;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipio, Integer> {
    List<Municipio> findAllOrdenado(Sort sort);

    List<Municipio> findBy();

    Municipio findMunicipioById(int id);

    Municipio findMunicipioByNombre(String nombre);

    List<Provincia> findByProvincia(Provincia provincia);

}

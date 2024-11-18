package com.example.ayuda_municipios.provincias;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciasRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findAllOrdenado(Sort sort);
    List<Provincia> findBy();
    Provincia findProvinciaById(int id);
    Provincia findProvinciaByNombre(String nombre);

}

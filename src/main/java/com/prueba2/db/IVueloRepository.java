package com.prueba2.db;

import com.prueba2.entities.Vuelo;
import org.springframework.context.annotation.Primary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface IVueloRepository extends CrudRepository<Vuelo, Integer> {

    Vuelo getReferenceById(Integer id);
}

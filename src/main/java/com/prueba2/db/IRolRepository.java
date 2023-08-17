package com.prueba2.db;

import com.prueba2.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Long> {

}

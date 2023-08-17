package com.prueba2.db;

import com.prueba2.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNombreUsuario(String nombreUsuario);

    Cliente findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    Cliente findByNombreUsuarioOrCorreo(String nombreUsuario, String correo);

    boolean existsByNombreUsuarioOrCorreo(String nombreUsuario, String correo);
}


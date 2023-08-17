
package com.prueba2.service;

import com.prueba2.entities.Cliente;
import java.util.List;

public interface IClienteService {
    // Se obtiene un listado de usuarios en un List
    public List<Cliente> getUsuarios();
    
    // Se obtiene un Usuario, a partir del id de un usuario
    public Cliente getUsuario(Cliente cliente);
    
    // Se obtiene un Usuario, a partir del username de un usuario
    public Cliente getUsuarioPorUsername(String nombreUsuario);

    // Se obtiene un Usuario, a partir del username y el password de un usuario
    public Cliente getUsuarioPorUsernameYPassword(String nombreUsuario, String contrasena);
    
    // Se obtiene un Usuario, a partir del username y el password de un usuario
    public Cliente getUsuarioPorUsernameOCorreo(String nombreUsuario, String correo);
    
    // Se valida si existe un Usuario considerando el username
    public boolean existeUsuarioPorUsernameOCorreo(String nombreUsuario, String correo);
    
    // Se inserta un nuevo usuario si el id del usuario esta vacío
    // Se actualiza un usuario si el id del usuario NO esta vacío
    public void save(Cliente cliente,boolean crearRolUser);
    
    // Se elimina el usuario que tiene el id pasado por parámetro
    public void delete(Cliente cliente);
}

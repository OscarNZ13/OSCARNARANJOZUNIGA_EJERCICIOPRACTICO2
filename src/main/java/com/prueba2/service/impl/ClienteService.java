package com.prueba2.service.impl;

import com.prueba2.db.IClienteRepository;
import com.prueba2.db.IRolRepository;
import com.prueba2.entities.Cliente;
import com.prueba2.entities.Rol;
import com.prueba2.service.IClienteService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService implements UserDetailsService, IClienteService {

    @Autowired
    private HttpSession session;
    @Autowired
    private IClienteRepository usuarioDao;
    @Autowired
    private IRolRepository rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getUsuario(Cliente usuario) {
        return usuarioDao.findById(usuario.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getUsuarioPorUsername(String nombreUsuario) {
        return usuarioDao.findByNombreUsuario(nombreUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getUsuarioPorUsernameYPassword(String nombreUsuario, String contrasena) {
        return usuarioDao.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getUsuarioPorUsernameOCorreo(String nombreUsuario, String correo) {
        return usuarioDao.findByNombreUsuarioOrCorreo(nombreUsuario, correo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String nombreUsuario, String correo) {
        return usuarioDao.existsByNombreUsuarioOrCorreo(nombreUsuario, correo);
    }

    @Override
    @Transactional
    public void save(Cliente usuario, boolean crearRolUser) {
        usuario = usuarioDao.save(usuario);
        if (crearRolUser)
        {//Si se está creando el usuario, se crea el rol por defecto "USER"
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setId(usuario.getId());
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional
    public void delete(Cliente usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        //Busca el usuario por el username en la tabla
        Cliente usuario = usuarioDao.findByNombreUsuario(nombreUsuario);
        //Si no existe el usuario lanza una excepción
        if (usuario == null)
        {
            throw new UsernameNotFoundException(nombreUsuario);
        }
        // Si está acá es porque existe el usuario... sacamos el rol que tiene
        var roles = new ArrayList<GrantedAuthority>();
        // Agregamos el rol del cliente a la lista de autoridades
        roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
        // Se devuelve User (clase de userDetails)
        return new User(usuario.getNombreUsuario(), usuario.getContrasena(), roles);
    }
}

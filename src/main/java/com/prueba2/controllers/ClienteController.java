package com.prueba2.controllers;

import com.prueba2.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.prueba2.service.impl.ClienteService;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService usuarioService;

    @GetMapping("/user")
    public String index(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "/user";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Cliente usuario) {
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Cliente usuario,
        @RequestParam("imagenFile") MultipartFile imagenFile) {
        usuarioService.save(usuario, true);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Cliente usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Cliente usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }
}

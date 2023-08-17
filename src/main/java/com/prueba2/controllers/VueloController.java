package com.prueba2.controllers;

import com.prueba2.entities.Vuelo;
import com.prueba2.service.IVueloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueloController {

    private final IVueloService productService;

    public VueloController(IVueloService productService) {
        this.productService = productService;
    }

    @GetMapping("/vuelos")
    public String index(Model model) {

        var baseProduct = new Vuelo();
        model.addAttribute("vueloDefault", baseProduct);
        model.addAttribute("vuelos", this.productService);
        return "vuelos";
    }

}

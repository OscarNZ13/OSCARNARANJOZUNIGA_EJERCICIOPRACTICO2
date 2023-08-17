package com.prueba2.controllers;

import com.prueba2.entities.Vuelo;
import com.prueba2.service.IVueloService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VueloController {

    private final IVueloService productService;

    public VueloController(IVueloService productService) {
        this.productService = productService;
    }

    @GetMapping("/vuelos")
    public String index(Model model, @RequestParam("lowerPrice") Optional<Integer> lowerPrice, @RequestParam("higherPrice") Optional<Integer> higherPrice) {

        var baseProduct = new Vuelo();
        model.addAttribute("productDefault", baseProduct);
        model.addAttribute("products", this.productService);
        return "vuelos";
    }

}

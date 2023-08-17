package com.prueba2.api;

import com.prueba2.entities.Vuelo;
import com.prueba2.service.IVueloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("api/vuelos")
public class VueloControllerAPI {
    private final IVueloService productService;

    public VueloControllerAPI(IVueloService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Vuelo> getAll() {
        return this.productService.getAll();
    }

    @GetMapping()
    public Vuelo getById(@RequestParam("id") int id) {
        var product = this.productService.getById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }

    @PostMapping()
    public Vuelo save(@RequestBody Vuelo product) {
        return this.productService.save(product);
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody Vuelo vuelo) {
        this.productService.delete(vuelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
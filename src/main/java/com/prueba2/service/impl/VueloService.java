
package com.prueba2.service.impl;

import com.prueba2.db.IVueloRepository;
import com.prueba2.entities.Vuelo;
import com.prueba2.service.IVueloService;
import org.springframework.stereotype.Service;

@Service
public class VueloService extends BaseService<Vuelo, Integer> implements IVueloService{
//here we have to create this variable because of syntax we can't see the this.repository as a IproductRepository
    private final IVueloRepository productRepository;

    public VueloService(IVueloRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }
}

package org.grupo14.msvcfactura.service;

import org.grupo14.msvcfactura.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.grupo14.msvcfactura.repository.FacturaRepository;

import java.util.List;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura delete(Factura factura) {
        facturaRepository.delete(factura);
        return factura;
    }

    public Factura update(Factura factura) {
        return facturaRepository.save(factura);
    }
}

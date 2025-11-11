package org.grupo14.msvcfactura.controller;

import org.grupo14.msvcfactura.entity.Factura;
import org.grupo14.msvcfactura.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    FacturaService facturaService;

    @GetMapping("")
    public ResponseEntity<List<Factura>> getAll(){
        List<Factura> facturas = facturaService.findAll();
        if(facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getById(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        if(factura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PostMapping("")
    public ResponseEntity<Factura> create(@RequestBody Factura factura){
        return  ResponseEntity.ok(facturaService.save(factura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> update(@PathVariable Long id, @RequestBody Factura factura){
        Factura f = facturaService.findById(id);
        if(f == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaService.save(factura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> delete(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        if(factura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaService.delete(factura));
    }

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World!";
    }
}

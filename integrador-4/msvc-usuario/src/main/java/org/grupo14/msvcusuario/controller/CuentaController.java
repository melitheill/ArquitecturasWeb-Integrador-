package org.grupo14.msvcusuario.controller;

import lombok.RequiredArgsConstructor;
import org.grupo14.msvcusuario.dto.CuentaDTO;
import org.grupo14.msvcusuario.entity.Cuenta;
import org.grupo14.msvcusuario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuenta")
@RequiredArgsConstructor
public class CuentaController {


    private final CuentaService cuentaService;

    @GetMapping("")
    public ResponseEntity<List<CuentaDTO>> getAll(){
        List<Cuenta> cuentas = cuentaService.getAll();
        if (cuentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuentaService.convertirDTO(cuentas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getById(@PathVariable Long id){
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuentaService.convertirDTO(cuenta));
    }

    @PostMapping("")
    public ResponseEntity<Cuenta> create(@RequestBody Cuenta cuenta){
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> update(@PathVariable Long id, @RequestBody Cuenta cuenta){
        Cuenta c = cuentaService.findById(id);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuentaService.update(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cuenta> delete(@PathVariable Long id){
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cuentaService.delete(cuenta));
        }
    }

    @PostMapping("anular/{id}")
    public ResponseEntity<Cuenta> anular(@PathVariable Long id){
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cuentaService.anularCuenta(cuenta));
        }
    }

    @PutMapping("cargar-saldo/{id}/{monto}")
    public ResponseEntity<Cuenta> cargarSaldo(@PathVariable Long id,@PathVariable Double monto){
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cuentaService.cargarSaldo(id, monto));
        }
    }

    @PutMapping("abonarViaje/{id}/{monto}")
    public ResponseEntity<Cuenta> abonarViaje(@PathVariable Long id,@PathVariable Double monto){
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cuentaService.abonarViaje(id, monto));
        }
    }

    //melit@MelinaTheill Arquitectura-Integrador/integrador-1 (main)
    //$ curl -X PUT http://localhost:8003/api/cuenta/cargar-saldo/1/500
    //{"nroCuenta":1,"saldo":7500.5,"fechaAlta":"2024-11-10T12:30:00.000+00:00","anulada":false}
    //melit@MelinaTheill /Arquitectura-Integrador/integrador-1 (main)
    //$  curl -X PUT http://localhost:8003/api/cuenta/abonarViaje/1/500
    //{"nroCuenta":1,"saldo":7000.5,"fechaAlta":"2024-11-10T12:30:00.000+00:00","anulada":false}

}
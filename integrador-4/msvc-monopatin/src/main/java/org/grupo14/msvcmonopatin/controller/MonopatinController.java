package org.grupo14.msvcmonopatin.controller;

import org.grupo14.msvcmonopatin.dto.MonopatinDTO;
import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.grupo14.msvcmonopatin.service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatin")
public class MonopatinController {

    @Autowired
    MonopatinService monopatinService;

    @RequestMapping("")
    public ResponseEntity<List<Monopatin>> getAll() {
        List<Monopatin> monopatins = monopatinService.findAll();
        if(monopatins.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monopatins);
    }

    @RequestMapping("/id")
    public ResponseEntity<Monopatin> getById(@PathVariable long id) {
        Monopatin monopatin = monopatinService.findById(id);
        if(monopatin == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monopatin);
    }

    @PostMapping("")
    public ResponseEntity<Monopatin> create(@RequestBody Monopatin monopatin) {
        return ResponseEntity.ok(monopatinService.save(monopatin));
    }

    @PutMapping("/id")
    public ResponseEntity<Monopatin> update(@PathVariable Long id,@RequestBody Monopatin monopatin) {
        Monopatin updatedMonopatin = monopatinService.findById(id);
        if(monopatin == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monopatinService.update(monopatin));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Monopatin> delete(@PathVariable Long id,@RequestBody Monopatin monopatin) {
        Monopatin deleteMonopatin = monopatinService.findById(id);
        if(monopatin == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monopatinService.delete(deleteMonopatin));

    }

    @GetMapping("/reporte")
    public ResponseEntity<List<MonopatinDTO>> getMonopatin() {
        List<MonopatinDTO> monopatines = monopatinService.reporte();
        System.out.println(monopatines);
        return ResponseEntity.ok(monopatines);
    }


}

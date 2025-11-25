package org.grupo14.mcsvviaje.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import org.grupo14.mcsvviaje.DTO.MonopatinDTOViajes;
import org.grupo14.mcsvviaje.DTO.VDTO;
import org.grupo14.mcsvviaje.DTO.ViajeDTO;
import org.grupo14.mcsvviaje.DTO.ViajeUsuarioDTO;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.repository.ViajeRepository;
import org.grupo14.mcsvviaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/viaje")
@RequiredArgsConstructor
public class ViajeController {


    private final ViajeService viajeService;

    private final ViajeRepository viajeRepository;

    @GetMapping("")
    public ResponseEntity<List<VDTO>> getAll(){
        List<Viaje> viajes = viajeService.getAll();
        if (viajes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(viajeService.convertirDTO(viajes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VDTO> getById(@PathVariable Long id){
        Viaje viaje = viajeService.findById(id);
        if (viaje == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(viajeService.convertirDTO(viaje));
    }

    @PostMapping("")
    public ResponseEntity<Viaje> create(@RequestBody Viaje viaje){
        viajeService.save(viaje);
        return ResponseEntity.ok(viaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> update(@PathVariable Long id,@RequestBody Viaje viaje){
        Viaje v = viajeService.findById(id);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(viajeService.update(viaje));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viaje> delete(@PathVariable Long id){
        Viaje viaje = viajeService.findById(id);
        if (viaje == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(viajeService.delete(viaje));
        }
    }

    @GetMapping("/reporte/{idMonopatin}")
    public ResponseEntity<List<ViajeDTO>> test(@PathVariable Long idMonopatin){
        return ResponseEntity.ok(viajeService.obtenerViajesDelMonopatin(idMonopatin));
    }
    @GetMapping("/usuario/{idUsuario}/{yearInicio}/{mesInicio}/{yearFin}/{mesFin}")
    public ResponseEntity<List<ViajeUsuarioDTO>> obtenerViajesPorUsuario(@PathVariable Long idUsuario, @PathVariable int yearInicio, @PathVariable int mesInicio,@PathVariable int yearFin,  @PathVariable int mesFin){
        return ResponseEntity.ok(viajeService.obtenerViajesPorUsuario(idUsuario, yearInicio,  mesInicio, yearFin,mesFin));
    }

    @GetMapping("/estadisticas/viajes-por-monopatin-cantidad/{anio}/{cantidad}")
    public ResponseEntity<List<MonopatinDTOViajes>> getCantidadViajesPorMonopatinMayor(@PathVariable int anio, @PathVariable int cantidad) {
        List<Object[]> resultados = viajeRepository.getCantidadViajesPorMonopatinMayor(anio, cantidad);
        List<MonopatinDTOViajes> res = new ArrayList<MonopatinDTOViajes>();
        for (Object[] o : resultados) {
            long idMonopatin = (long) o[0];
            Long cant = (Long)o[1];
            res.add(new MonopatinDTOViajes(idMonopatin, cant));
        }

        return ResponseEntity.ok(res);
    }
}

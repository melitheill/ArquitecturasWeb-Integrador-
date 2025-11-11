package org.grupo14.mcsvviaje.service;

import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.model.Factura;
import org.grupo14.mcsvviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    public List<Viaje> getAll(){
        return viajeRepository.findAll();
    }

    public Viaje save(Viaje viaje){
        return viajeRepository.save(viaje);
    }

    public Viaje delete(Viaje viaje){
        viajeRepository.delete(viaje);
        return viaje;
    }

    public Viaje findById(Long id){
        return viajeRepository.findById(id).orElse(null);
    }

    public Viaje update(Viaje viaje){
        return viajeRepository.save(viaje);
    }

    public void facturar(Long id){
        Viaje viaje = findById(id);
        if(viaje != null){
            LocalDate date = viaje.getFechaHoraFin().toLocalDateTime().toLocalDate();
            int valor = viaje.getKmRecorridos() * viaje.getTarifa() + viaje.getKmRecorridosPausaExtensa() * viaje.getTarifa();
            Factura factura = new Factura(date, valor, viaje.getId());
            //facturar
        }
    }
}

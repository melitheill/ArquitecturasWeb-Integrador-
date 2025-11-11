package org.grupo14.mcsvviaje.service;

import org.grupo14.mcsvviaje.entity.Tiempo;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.model.Factura;
import org.grupo14.mcsvviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    TiempoService tiempoService;

    @Autowired
    RestTemplate restTemplate;

    public List<Viaje> getAll(){
        return viajeRepository.findAll();
    }

    public Viaje save(Viaje viaje){
        int time = calcularTiempo(viaje);
        Tiempo tiempo = new Tiempo();
        tiempo.setTiempoSinPausa(time);
        tiempo.setTiempoConPausa(time + viaje.getPausa());
        viaje.setTiempo(tiempo);
        tiempoService.save(tiempo);
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

    public String facturar(Long id){
        Viaje viaje = findById(id);
        if(viaje != null){
            LocalDate date = viaje.getFechaHoraFin().toLocalDateTime().toLocalDate();
            int valor = viaje.getTiempo().getTiempoSinPausa() * viaje.getTarifa() + viaje.getPausa() * viaje.getTarifa();
            Factura factura = new Factura(date, valor, viaje.getId(), calcularTiempo(viaje));
            restTemplate.postForObject("http://localhost:8002/factura", factura, String.class);
            calcularTiempo(viaje);
            return "Facturado correctamente, tiempo: ";
        }
        return "Factura no encontrada";
    }

    public int calcularTiempo(Viaje viaje){
        Timestamp fechaHoraFin = viaje.getFechaHoraFin();
        Timestamp fechaHoraInicio = viaje.getFechaHoraInicio();
        int horaInicioInSeconds = fechaHoraInicio.getHours() * 3600 + fechaHoraInicio.getMinutes() * 60 + fechaHoraInicio.getSeconds();
        int horaFinInSeconds = fechaHoraFin.getHours() * 3600 + fechaHoraFin.getMinutes() * 60 + fechaHoraFin.getSeconds();
        int diferenciaMinutos = (horaFinInSeconds - horaInicioInSeconds)/60;
        return diferenciaMinutos;
    }
}

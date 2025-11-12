package org.grupo14.mcsvviaje.service;

import org.grupo14.mcsvviaje.DTO.ViajeDTO;
import org.grupo14.mcsvviaje.DTO.ViajeUsuarioDTO;
import org.grupo14.mcsvviaje.entity.Tiempo;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.feignClients.FacturaFeignClient;
import org.grupo14.mcsvviaje.model.Factura;
import org.grupo14.mcsvviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    TiempoService tiempoService;

    @Autowired
    FacturaFeignClient facturaFeignClient;

    public List<Viaje> getAll(){
        return viajeRepository.findAll();
    }

    public Viaje save(Viaje viaje){
        int time = calcularTiempo(viaje);
        Tiempo tiempo = new Tiempo();
        tiempo.setTiempoSinPausas(time);
        tiempo.setTiempoPausado(time + viaje.getPausa());
        viaje.setTiempo(tiempo);
        tiempoService.save(tiempo);
        facturar(viaje);
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

    public void facturar(Viaje viaje){
        LocalDate date = viaje.getFechaHoraFin().toLocalDateTime().toLocalDate();

        Factura factura = new Factura(date, viaje.getKmRecorridos(),viaje.getKmRecorridosPausaExtensa());
        facturaFeignClient.save(factura);
    }

    public int calcularTiempo(Viaje viaje){
        Timestamp fechaHoraInicio = viaje.getFechaHoraInicio();
        Timestamp fechaHoraFin = viaje.getFechaHoraFin();
        int horaInicioInMinutes = fechaHoraInicio.getHours() * 60 +  fechaHoraInicio.getMinutes();
        int horaFinInMinutes = fechaHoraFin.getHours() * 60 +  fechaHoraFin.getMinutes();
        return horaFinInMinutes -  horaInicioInMinutes;
    }

    public List<ViajeDTO> obtenerViajesDelMonopatin(Long idMonopatin){
        List<Viaje> viajes = viajeRepository.obtenerViajesPorMonopatin(idMonopatin);
        List<ViajeDTO> viajeDTOs = new ArrayList<>();
        for (Viaje viaje : viajes) {
            ViajeDTO viajeDTO = new ViajeDTO();
            viajeDTO.setId(viaje.getId());
            viajeDTO.setTiempoSinPausas(viaje.getTiempo().getTiempoSinPausas());
            viajeDTO.setTiempoConPausas(viaje.getTiempo().getTiempoPausado());
            viajeDTO.setKmRecorridos(viaje.getKmRecorridos());
            viajeDTOs.add(viajeDTO);
        }
        return viajeDTOs;
    }

    public List<ViajeUsuarioDTO> obtenerViajesPorUsuario(Long idUsuario, int yearInicio, int mesInicio, int yearFin,  int mesFin) {
        if (yearFin < yearInicio){
            return null;
        }

        List<Viaje> viajes =  viajeRepository.obtenerViajesPorUsuario(idUsuario, yearInicio, mesInicio, yearFin,  mesFin);
        List<ViajeUsuarioDTO> viajesDTO = new ArrayList<>();
        for (Viaje viaje : viajes) {
            ViajeUsuarioDTO viajeDTO = new ViajeUsuarioDTO(viaje.getKmRecorridos()+ viaje.getKmRecorridosPausaExtensa(), viaje.getTiempo().getTiempoSinPausas(), viaje.getTiempo().getTiempoPausado());
            viajesDTO.add(viajeDTO);
        }
        return viajesDTO;
    }
}

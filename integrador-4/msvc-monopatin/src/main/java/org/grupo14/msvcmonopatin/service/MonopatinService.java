package org.grupo14.msvcmonopatin.service;

import feign.Client;
import lombok.RequiredArgsConstructor;
import org.grupo14.msvcmonopatin.dto.MonopatinDTO;
import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.grupo14.msvcmonopatin.feignClients.ParadaFeignClient;
import org.grupo14.msvcmonopatin.feignClients.ViajeFeignClient;
import org.grupo14.msvcmonopatin.model.Parada;
import org.grupo14.msvcmonopatin.model.Viaje;
import org.grupo14.msvcmonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonopatinService {


    private final MonopatinRepository monopatinRepository;

    private final ViajeFeignClient viajeFeignClient;
    private final ParadaFeignClient paradaFeignClient;

    public List<Monopatin> findAll() {
        return monopatinRepository.findAll();
    }

    public Monopatin findById(long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public Monopatin save(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);

    }

    public Monopatin update(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public Monopatin delete(Monopatin monopatin) {
        monopatinRepository.delete(monopatin);
        return monopatin;
    }

    public List<MonopatinDTO> reporte(){
        List<Monopatin> monopatines =  monopatinRepository.findAll();
        List<MonopatinDTO> monopatinesDTO = new ArrayList<>();
        for (Monopatin monopatin : monopatines) {
            List<Viaje> viajes = viajeFeignClient.reporte(monopatin.getId());
            int tiempoSinPausas = 0;
            int tiempoConPausas = 0;
            int kmRecorridos = 0;
            for (Viaje viaje : viajes) {
                tiempoSinPausas = tiempoSinPausas +  viaje.getTiempoSinPausas();
                tiempoConPausas = tiempoConPausas +  viaje.getTiempoConPausas();
                kmRecorridos =  kmRecorridos + viaje.getKmRecorridos();
            }
            MonopatinDTO monopatinDTO = new MonopatinDTO(monopatin.getId(), kmRecorridos, tiempoSinPausas, tiempoConPausas);
            monopatinesDTO.add(monopatinDTO);
        }
        monopatinesDTO.sort( (m,n) -> m.getKmRecorridos() > n.getKmRecorridos() ? -1 : 1 );
        return monopatinesDTO;
    }

    public String setMantenimiento(long monopatinId) {
        Monopatin monopatin = findById(monopatinId);
        if(monopatin == null){
            return "Monopatin no encontrado";
        }
        monopatin.setEstado("mantenimiento");
        monopatin.setLatitud(0);
        monopatin.setLongitud(0);
        monopatinRepository.save(monopatin);
        return "Monopatin en mantenimiento";
    }

   public List<Monopatin> findByZona(double lat,double lon){
       return monopatinRepository.findByZona(lat,lon);
  }

   public List<Monopatin> findbyParada(Long id){
        Parada parada = paradaFeignClient.getParada(id);
        if(parada == null){
            throw new RuntimeException("Parada no encontrada con id: " + id);
        }
        return monopatinRepository.findByZona(parada.getLatitud(),parada.getLongitud());
   }
}

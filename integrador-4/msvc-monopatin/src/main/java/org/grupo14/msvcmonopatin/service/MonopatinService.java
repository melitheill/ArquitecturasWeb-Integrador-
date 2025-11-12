package org.grupo14.msvcmonopatin.service;

import feign.Client;
import org.grupo14.msvcmonopatin.dto.MonopatinDTO;
import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.grupo14.msvcmonopatin.feignClients.ViajeFeignClient;
import org.grupo14.msvcmonopatin.model.Viaje;
import org.grupo14.msvcmonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Autowired
    RestTemplate restTemplate;

    private ViajeFeignClient viajeFeignClient;
    private Client feignClient;

    public MonopatinService(ViajeFeignClient viajeFeignClient) {
        this.viajeFeignClient = viajeFeignClient;
    }

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
                System.out.println(viaje.getTiempoSinPausas());
                System.out.println(viaje.getTiempoConPausas());
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
}

package org.example.msvcparada.service;

import lombok.RequiredArgsConstructor;
import org.example.msvcparada.DTO.ParadaDTO;
import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParadaService {


    private final ParadaRepository paradaRepository;

    public List<ParadaDTO> convertirDTO(List<Parada> paradas){
        List<ParadaDTO> paradaDTO = new ArrayList<>();
        for(Parada parada : paradas){
            paradaDTO.add(convertirDTO(parada));
        }
        return paradaDTO;
    }
    public ParadaDTO convertirDTO(Parada parada){
        return  new ParadaDTO(parada.getNombre(), parada.getLatitud(), parada.getLongitud());
    }

    public List<Parada> getAll() {return paradaRepository.findAll();}

    public Parada save(Parada tarifa) {return paradaRepository.save(tarifa);}

    public Parada delete(Parada tarifa) {
        paradaRepository.delete(tarifa);
        return tarifa;
    }

    public Parada findById(Long id) {return paradaRepository.findById(id).orElse(null);}

    public Parada update(Parada tarifa) {return paradaRepository.save(tarifa);}
}

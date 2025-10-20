package org.grupo14.integrador3.dto;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class ReporteDTO {
    String carrera;
    Map<Integer, CarreraInfoDTO> infoAnual;

    public ReporteDTO(String carrera){
        this.carrera = carrera;
        this.infoAnual = new TreeMap<>();
    }
}

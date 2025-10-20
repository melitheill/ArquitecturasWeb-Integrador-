package org.grupo14.integrador3.dto;

import lombok.Data;

@Data
public class CarreraInfoDTO {
    private int inscriptos;
    private int egresados;

    public CarreraInfoDTO(int inscriptos) {
        this.inscriptos = inscriptos;
        this.egresados = 0;
    }


}

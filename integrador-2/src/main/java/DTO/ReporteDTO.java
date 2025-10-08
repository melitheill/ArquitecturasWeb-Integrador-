package DTO;

import java.util.Map;
import java.util.TreeMap;

public class ReporteDTO {
    String nombreCarrera;
    Map<Integer, CarreraInfoDTO> infoPorAnio;

    public ReporteDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.infoPorAnio = new TreeMap<>();
    }

    @Override
    public String toString() {
        return "\nCarrera = " + nombreCarrera + ", " +
                "\nInformacion de alumnos por año \n" + infoPorAnio;
    }

    public Map<Integer, CarreraInfoDTO> getInfoPorAnio() {
        return infoPorAnio;
    }
}

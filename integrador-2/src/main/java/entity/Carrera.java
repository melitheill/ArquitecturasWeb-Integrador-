package entity;

import entity.Identificador.Identificador;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrera{

    @EmbeddedId
    private Identificador id;
    @Column
    private String carrera;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudianteCarrera;

    public Carrera() {
    }
    public Carrera(int id, String carrera, int duracion) {
        this.id = new Identificador(id);
        this.carrera = carrera;
        this.duracion = duracion;
    }

    public int getId() {
        return id.getId();
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}

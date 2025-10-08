package entity;

import entity.Identificador.Identificador;

import javax.persistence.*;
import java.util.List;

@Entity
public class Estudiante{
    @EmbeddedId
    private Identificador DNI;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @Column
    private int LU;

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> estudianteCarrera;

    public Estudiante() {
    }

    public Estudiante(int DNI, String nombre, String apellido, int edad, String genero, String ciudad, int LU) {
        this.DNI = new Identificador(DNI);
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.LU = LU;
    }

    public int getDNI() {
        return DNI.getId();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getLU() {
        return LU;
    }

    public void setLU(int LU) {
        this.LU = LU;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "apellido='" + apellido + '\'' +
                '}';
    }
}

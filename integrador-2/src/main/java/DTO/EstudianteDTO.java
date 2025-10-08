package DTO;

public class EstudianteDTO {
    private String nombre;
    private String apellido;

    public EstudianteDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}

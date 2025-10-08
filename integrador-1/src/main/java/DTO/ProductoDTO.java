package DTO;

public class ProductoDTO {
    String nombre;
    int recaudacion;

    public ProductoDTO(String nombre, int recaudacion) {
        this.nombre = nombre;
        this.recaudacion = recaudacion;
    }

    @Override
    public String toString() {
        return "nombre = " + nombre + ", recaudacion = " + recaudacion;
    }
}

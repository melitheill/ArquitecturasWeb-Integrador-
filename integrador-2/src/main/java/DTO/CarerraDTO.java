package DTO;

public class CarerraDTO {

    private String nombre;
    private int cantidad;

    public CarerraDTO(String carrera, int cantidad) {
          this.nombre = carrera;
          this.cantidad = cantidad;
    }
    @Override
    public String toString() {

        return  "\nnombre='" + nombre + '\'' +
                    ", inscriptos=" + cantidad;
        }

}

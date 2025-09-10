package DTO;

public class ClienteDTO {
    private String nombre;
    int facturacion;

    public ClienteDTO(String nombre, int facturacion) {
        this.nombre = nombre;
        this.facturacion = facturacion;
    }

    @Override
    public String toString() {
        return "\nnombre=" + nombre +", facturacion=" + facturacion;
    }
}

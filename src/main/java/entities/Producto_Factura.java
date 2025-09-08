package entities;

public class Producto_Factura {
    private int id_producto_factura;
    private String nombreProducto;
    private int valorProducto;

    public Producto_Factura(int id_producto_factura, String nombreProducto, int valorProducto) {
        this.id_producto_factura = id_producto_factura;
        this.nombreProducto = nombreProducto;
        this.valorProducto = valorProducto;
    }

    public int getId_producto_factura() {
        return id_producto_factura;
    }

    public void setId_producto_factura(int id_producto_factura) {
        this.id_producto_factura = id_producto_factura;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(int valorProducto) {
        this.valorProducto = valorProducto;
    }

    @Override
    public String toString() {
        return "Producto_Factura{" +
                "id_producto_factura=" + id_producto_factura +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", valorProducto=" + valorProducto +
                '}';
    }
}

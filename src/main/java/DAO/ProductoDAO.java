package DAO;

import entities.Producto;

public interface ProductoDAO extends CRUD<Producto> {
    public Producto getProductoMayorFacturacion();
}

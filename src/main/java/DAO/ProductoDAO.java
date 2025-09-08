package DAO;

import entities.Producto;

import java.sql.SQLException;

public interface ProductoDAO extends CRUD<Producto> {
    public Producto getProductoMayorFacturacion();
}

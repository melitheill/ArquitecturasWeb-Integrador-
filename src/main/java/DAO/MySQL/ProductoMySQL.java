package DAO.MySQL;

import DAO.ProductoDAO;
import entities.Producto;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductoMySQL implements ProductoDAO {
    private Connection conn;

    public ProductoMySQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Producto getProductoMayorFacturacion() {
        return null;
    }

    @Override
    public void insert(Producto entity) throws SQLException {

    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Producto entity) throws SQLException {
        return false;
    }

    @Override
    public Producto find(int id) throws SQLException {
        return null;
    }
}

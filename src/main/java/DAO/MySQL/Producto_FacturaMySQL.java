package DAO.MySQL;

import DAO.Producto_FacturaDAO;

import java.sql.Connection;

public class Producto_FacturaMySQL implements Producto_FacturaDAO {
    private Connection conn;

    public Producto_FacturaMySQL(Connection conn) {
        this.conn = conn;
    }
}

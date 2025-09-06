package DAO;

import java.sql.Connection;

public class Producto_FacturaDAO {
    private Connection conn;

    public Producto_FacturaDAO(Connection conn) {
        this.conn = conn;
    }
}

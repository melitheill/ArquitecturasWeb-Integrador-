package DAO.MySQL;

import DAO.ProductoDAO;

import java.sql.Connection;

public class ProductoMySQL implements ProductoDAO {
    private Connection conn;

    public ProductoMySQL(Connection conn) {
        this.conn = conn;
    }
}

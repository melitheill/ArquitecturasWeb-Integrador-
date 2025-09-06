package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/arquiDB";

//        try {
//            Class.forName(driver).getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() throws SQLException {
        String tablaPersona  = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY," +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150));";
        this.conn.prepareStatement(tablaPersona).execute();
    }

}

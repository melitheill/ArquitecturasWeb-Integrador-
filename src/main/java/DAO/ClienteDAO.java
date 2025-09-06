package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    private Connection conn;
    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }
    public void selectAll() throws SQLException {
        String select = "SELECT * FROM Cliente";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idCliente = rs.getInt("idCliente");
            String nombre = rs.getString("nombre");
            String email = rs.getString("email");
            System.out.println( idCliente + " " + nombre + " " + email);
        }
    }

    public void insertCliente(int idCliente, String nombre, String email) throws SQLException {
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, idCliente);
        ps.setString(2, nombre);
        ps.setString(3, email);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
}

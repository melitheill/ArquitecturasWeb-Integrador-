package DAO;

import entities.Cliente;
import entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaDAO {
    private Connection conn;
    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFactura(int idFactura, int idCliente) throws SQLException {
        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, idFactura);
        ps.setInt(2, idCliente);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public boolean delete(int idFactura) throws SQLException {
        if (find(idFactura) == null){
            return false;
        }
        String delete = "DELETE FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = this.conn.prepareStatement(delete);
        ps.setInt(1, idFactura);
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }

    public Factura find(int idFactura) throws SQLException {
        Factura factura = null;
        String select = "SELECT * FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, idFactura);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int idCliente = rs.getInt(2);
            factura = new Factura(idFactura, idCliente);
        }

        return factura;
    }

    public boolean update(int idFactura, int idCliente) throws SQLException {
        if (find(idFactura) == null){
            return false;
        }
        String update = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
        PreparedStatement ps = this.conn.prepareStatement(update);
        ps.setInt(1, idCliente);
        ps.setInt(2, idFactura);
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }
}

package DAO.MySQL;

import DAO.FacturaDAO;
import entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaMySQL implements FacturaDAO {
    private Connection conn;
    public FacturaMySQL(Connection conn) {
        this.conn = conn;
    }

    public void insert(Factura factura) throws SQLException {
        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());
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

    public boolean update(Factura factura) throws SQLException {
        if (find(factura.getIdFactura()) == null){
            return false;
        }
        String update = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
        PreparedStatement ps = this.conn.prepareStatement(update);
        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }
}

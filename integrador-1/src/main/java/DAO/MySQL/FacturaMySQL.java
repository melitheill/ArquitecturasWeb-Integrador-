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
        try{
            String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(insert);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error al insertar la factura");
        }
    }

    public boolean delete(Factura factura) throws SQLException {
        if (find(factura) == null){
            return false;
        }
        try{
            String delete = "DELETE FROM Factura WHERE idFactura = ?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, factura.getIdFactura());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar la factura");
            return false;
        }
    }

    public Factura find(Factura factura) throws SQLException {
        Factura fc = null;
        String select = "SELECT * FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, factura.getIdFactura());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int idCliente = rs.getInt(2);
            fc = new Factura(factura.getIdFactura(), idCliente);
        }

        return fc;
    }

    public boolean update(Factura factura) throws SQLException {
        if (find(factura) == null){
            return false;
        }
        try{
            String update = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdFactura());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la factura");
            return false;
        }
    }
}

package DAO.MySQL;

import DAO.Factura_ProductoDAO;

import entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Factura_ProductoMySQL implements Factura_ProductoDAO {
    private Connection conn;

    public Factura_ProductoMySQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Factura_Producto factura_producto) {
        try {
            String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(insert);
            ps.setInt(1, factura_producto.getIdFactura());
            ps.setInt(2, factura_producto.getIdProducto());
            ps.setInt(3, factura_producto.getCantidad());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto en la factura");
        }
    }

    @Override
    public boolean delete(Factura_Producto factura_producto) throws SQLException {
        if (find(factura_producto) == null){
            return false;
        }
        try {
            String delete = "DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, factura_producto.getIdFactura());
            ps.setInt(2, factura_producto.getIdProducto());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto en la factura");
            return false;
        }
    }

    @Override
    public boolean update(Factura_Producto facturaProducto) throws SQLException {
        if (find(facturaProducto) == null){
            return false;
        }
        try{
            String update = "UPDATE Factura_Producto SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setInt(1, facturaProducto.getCantidad());
            ps.setInt(2, facturaProducto.getIdFactura());
            ps.setInt(3, facturaProducto.getIdProducto());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto en la factura");
            return false;
        }
    }

    @Override
    public Factura_Producto find(Factura_Producto factura_producto) throws SQLException {
        Factura_Producto fp  = null;
        String select = "SELECT * FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, factura_producto.getIdFactura());
        ps.setInt(2, factura_producto.getIdProducto());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int cantidad = rs.getInt(3);

            fp = new Factura_Producto(factura_producto.getIdFactura(), factura_producto.getIdProducto(), cantidad);
        }

        return fp;
    }
}

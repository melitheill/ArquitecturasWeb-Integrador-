package DAO.MySQL;

import DAO.Producto_FacturaDAO;

import entities.Producto_Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto_FacturaMySQL implements Producto_FacturaDAO {
    private Connection conn;

    public Producto_FacturaMySQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Producto_Factura prod_fractura) throws SQLException {
        String insert = "INSERT INTO Producto_Factura (id_producto_factura,nombreProducto, valorProducto) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, prod_fractura.getId_producto_factura());
        ps.setString(2, prod_fractura.getNombreProducto());
        ps.setInt(3, prod_fractura.getValorProducto());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    @Override
    public boolean delete(int id_producto_factura) throws SQLException {
        if (find(id_producto_factura) == null){
            return false;
        }
        String delete = "DELETE FROM Producto_Factura WHERE id_producto_factura = ?";
        PreparedStatement ps = this.conn.prepareStatement(delete);
        ps.setInt(1, id_producto_factura);
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }

    @Override
    public boolean update(Producto_Factura producto_factura) throws SQLException {
        if (find(producto_factura.getId_producto_factura()) == null){
            return false;
        }
        String update = "UPDATE Producto_Factura SET nombreProducto = ?, valorProducto = ? WHERE id_producto_factura = ?";
        PreparedStatement ps = this.conn.prepareStatement(update);
        ps.setInt(1, producto_factura.getId_producto_factura());
        ps.setString(2, producto_factura.getNombreProducto());
        ps.setInt(3, producto_factura.getValorProducto());
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }

    @Override
    public Producto_Factura find(int id_producto_factura) throws SQLException {
        Producto_Factura pd  = null;
        String select = "SELECT * FROM Producto_Factura WHERE id_producto_factura = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, id_producto_factura);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombreProducto = rs.getString(2);
            int valorProdcuto = rs.getInt(3);

            pd = new Producto_Factura(id_producto_factura, nombreProducto, valorProdcuto);
        }

        return pd;
    }
}

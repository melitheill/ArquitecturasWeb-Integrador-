package DAO.MySQL;

import DAO.ProductoDAO;
import entities.Producto;
import entities.Producto_Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductoMySQL implements ProductoDAO {
    private Connection conn;

    public ProductoMySQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Producto getProductoMayorFacturacion() {
        return null;
    }

    @Override
    public void insert(Producto producto) throws SQLException {
        String insert = "INSERT INTO Producto (id_producto,nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    @Override
    public boolean delete(int id_producto) throws SQLException {
        if(find(id_producto)==null){
            return false;
        }
        String delete = "DELETE FROM Producto WHERE id_producto = ?";
        PreparedStatement ps = this.conn.prepareStatement(delete);
        ps.setInt(1, id_producto);
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }

    @Override
    public boolean update(Producto producto) throws SQLException {
        if (find(producto.getIdProducto()) == null){
            return false;
        }
        String update = "UPDATE Producto SET nombre = ?, valor = ? WHERE id_producto = ?";
        PreparedStatement ps = this.conn.prepareStatement(update);
        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        ps.executeUpdate();
        ps.close();
        conn.commit();
        return true;
    }

    @Override
    public Producto find(int id_producto) throws SQLException {
        Producto pd  = null;
        String select = "SELECT * FROM Producto WHERE id_producto = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, id_producto);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString(2);
            Float valor = rs.getFloat(3);

            pd = new Producto (id_producto, nombre, valor);
        }

        return pd;
    }

}

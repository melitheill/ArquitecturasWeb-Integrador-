package DAO.MySQL;

import DAO.ProductoDAO;
import entities.Producto;

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
    public void insert(Producto producto) {
        try{
            String insert = "INSERT INTO Producto (idProducto,nombre, valor) VALUES (?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(insert);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto");
        }
    }

    @Override
    public boolean delete(int id_producto) throws SQLException {
        if(find(id_producto)==null){
            return false;
        }
        try{
            String delete = "DELETE FROM Producto WHERE idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, id_producto);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch(SQLException e){
            System.out.println("Error al eliminar el producto");
            return false;
        }
    }

    @Override
    public boolean update(Producto producto) throws SQLException {
        if (find(producto.getIdProducto()) == null){
            return false;
        }
        try{
            String update = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getValor());
            ps.setInt(3, producto.getIdProducto());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto");
            return false;
        }
    }

    @Override
    public Producto find(int id_producto) throws SQLException {
        Producto pd  = null;
        String select = "SELECT * FROM Producto WHERE idProducto = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, id_producto);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString(2);
            float valor = rs.getFloat(3);
            pd = new Producto (id_producto, nombre, valor);
        }

        return pd;
    }

}

package DAO.MySQL;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteMySQL implements ClienteDAO {
    private Connection conn;
    public ClienteMySQL(Connection conn) {
        this.conn = conn;
    }

    public void insert(Cliente cliente) {
        try{
            String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(insert);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error al insertar el cliente");
        }
    }

    public boolean delete(Cliente cliente) throws SQLException {
        if (find(cliente) == null){
            return false;
        }
        try{
            String delete = "DELETE FROM Cliente WHERE idCliente = ?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, cliente.getIdCliente());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e){
            System.out.println("Error al eliminar el cliente");
            return false;
        }
    }

    public Cliente find(Cliente cliente) throws SQLException {
        Cliente cl = null;
        String select = "SELECT * FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, cliente.getIdCliente());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString(2);
            String email = rs.getString(3);
            cl = new Cliente(cliente.getIdCliente(), nombre, email);
        }
        return cl;
    }

    public boolean update(Cliente cliente) throws SQLException {
        if (find(cliente) == null){
            return false;
        }
        try{
            String update = "UPDATE Cliente SET nombre = ?, email = ? WHERE idCliente = ?";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente");
            return false;
        }
    }

    public List<ClienteDTO> getClientesByFacturacion() throws SQLException {
        String sql = "SELECT c.idCliente, c.nombre, c.email, " +
                "COALESCE(SUM(p.valor * fp.cantidad), 0) AS totalFacturado " +
                "FROM Cliente c " +
                "LEFT JOIN Factura f ON c.idCliente = f.idCliente " +
                "LEFT JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "LEFT JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY totalFacturado DESC;";

        List<ClienteDTO> list = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int facturado = rs.getInt("totalFacturado");
                String nombre = rs.getString("nombre");
                ClienteDTO cliente = new ClienteDTO(nombre,facturado);
                list.add(cliente);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

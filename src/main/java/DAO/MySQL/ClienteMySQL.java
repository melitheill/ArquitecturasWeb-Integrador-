package DAO.MySQL;

import DAO.ClienteDAO;
import entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean delete(int idCliente) throws SQLException {
        if (find(idCliente) == null){
            return false;
        }
        try{
            String delete = "DELETE FROM Cliente WHERE idCliente = ?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e){
            System.out.println("Error al eliminar el cliente");
            return false;
        }
    }

    public Cliente find(int idCliente) throws SQLException {
        Cliente cliente = null;
        String select = "SELECT * FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ps.setInt(1, idCliente);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString(2);
            String email = rs.getString(3);
            cliente = new Cliente(idCliente, nombre, email);
        }
        return cliente;
    }

    public boolean update(Cliente cliente) throws SQLException {
        if (find(cliente.getIdCliente()) == null){
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

    public List<Cliente> getClientesByFacturacion() throws SQLException {
        return null;
    }
}

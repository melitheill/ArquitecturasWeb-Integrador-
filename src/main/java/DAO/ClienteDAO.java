package DAO;

import entities.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO extends CRUD<Cliente> {
    public List<Cliente> getClientesByFacturacion() throws SQLException;
}

package DAO;

import DTO.ClienteDTO;
import entities.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO extends CRUD<Cliente> {
    public List<ClienteDTO> getClientesByFacturacion() throws SQLException;
}

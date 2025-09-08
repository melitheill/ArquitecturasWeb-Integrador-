package org.example;

import DAO.ClienteDAO;
import factory.AbstractFactory;
import utils.HelperMySQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        HelperMySQL db = new HelperMySQL();
        db.dropTables();
        db.createTables();
        db.importData(cliente);
//        cliente.selectAll();
//        cliente.insertCliente(2, "Meli", "meli@meli.meli");z
    }
}
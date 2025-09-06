package org.example;

import DAO.ClienteDAO;
import factory.AbstractFactory;
import utils.HelperMySQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        HelperMySQL db = new HelperMySQL();
        db.createTable();

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        cliente.selectAll();

    }
}
package org.example;

import DAO.MySQL.ClienteMySQL;
import factory.AbstractFactory;
import utils.HelperMySQL;

public class Main {
    public static void main(String[] args) throws Exception {
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteMySQL cliente = chosenFactory.getClienteDAO();
        HelperMySQL db = new HelperMySQL();
        db.dropTables();
        db.createTables();
        db.importData(cliente);
//        cliente.selectAll();
//        cliente.insertCliente(2, "Meli", "meli@meli.meli");z
    }
}
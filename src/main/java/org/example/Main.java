package org.example;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.MySQL.ClienteMySQL;
import DAO.ProductoDAO;
import entities.Cliente;
import entities.Factura;
import entities.Producto;
import factory.AbstractFactory;
import utils.HelperMySQL;

public class Main {
    public static void main(String[] args) throws Exception {
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();

        HelperMySQL db = new HelperMySQL();
//        db.dropTables();
//        db.createTables();
//        db.importData(cliente, producto, factura);
    }
}
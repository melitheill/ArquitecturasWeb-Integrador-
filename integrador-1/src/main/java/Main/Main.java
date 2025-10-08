package Main;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.MySQL.ClienteMySQL;
import DAO.ProductoDAO;
import entities.Cliente;
import entities.Factura;
import entities.Factura_Producto;
import entities.Producto;
import factory.AbstractFactory;
import utils.HelperMySQL;

public class Main {
    public static void main(String[] args) throws Exception {
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        Factura_ProductoDAO facturaProducto = chosenFactory.getProducto_FacturaDAO();

        HelperMySQL db = new HelperMySQL();
        db.dropTables();
        db.createTables();
        db.importData(cliente, producto, factura, facturaProducto);
        System.out.println(producto.getProductoMayorRecaudacion());
        System.out.println(cliente.getClientesByFacturacion());
    }
}
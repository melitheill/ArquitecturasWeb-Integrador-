package factory;

import DAO.MySQL.ClienteMySQL;
import DAO.MySQL.FacturaMySQL;
import DAO.MySQL.ProductoMySQL;
import DAO.MySQL.Factura_ProductoMySQL;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public abstract ClienteMySQL getClienteDAO();
    public abstract FacturaMySQL getFacturaDAO();
    public abstract ProductoMySQL getProductoDAO();
    public abstract Factura_ProductoMySQL getProducto_FacturaDAO();



    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : return MySQLFactory.getInstance();
            case DERBY_JDBC : return null;
            default: return null;
        }
    }
}

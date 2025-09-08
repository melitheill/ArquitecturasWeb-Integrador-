package factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import DAO.Producto_FacturaDAO;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract Producto_FacturaDAO getProducto_FacturaDAO();



    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : return MySQLFactory.getInstance();
            case DERBY_JDBC : return null;
            default: return null;
        }
    }
}

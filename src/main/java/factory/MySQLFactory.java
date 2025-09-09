package factory;

import DAO.MySQL.ClienteMySQL;
import DAO.MySQL.FacturaMySQL;
import DAO.MySQL.ProductoMySQL;
import DAO.MySQL.Factura_ProductoMySQL;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLFactory extends AbstractFactory {

    private static MySQLFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/arquiDB";
    public static Connection conn;

    private MySQLFactory() {
    }

    public static synchronized MySQLFactory getInstance() {
        if (instance == null) {
            instance = new MySQLFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ClienteMySQL getClienteDAO(){
        return new ClienteMySQL(createConnection());
    };
    public FacturaMySQL getFacturaDAO(){
        return new FacturaMySQL(createConnection());
    };
    public ProductoMySQL getProductoDAO(){
        return new ProductoMySQL(createConnection());
    };
    public Factura_ProductoMySQL getProducto_FacturaDAO(){
        return new Factura_ProductoMySQL(createConnection());
    };
}

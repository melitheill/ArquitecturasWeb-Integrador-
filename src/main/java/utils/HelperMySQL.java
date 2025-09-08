package utils;

import DAO.ClienteDAO;
import factory.MySQLFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class HelperMySQL {
    private Connection conn = null;
    private ClienteDAO clienteDAO;

    public HelperMySQL() {
        this.conn = MySQLFactory.createConnection();

//        try {
//            Class.forName(driver).getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

//        try {
//            conn = DriverManager.getConnection(uri, "root", "");
//            conn.setAutoCommit(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void dropTables() throws SQLException {
        try{
            String sql = "DROP TABLE Cliente";
            this.conn.prepareStatement(sql).execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createTables() throws SQLException {
        String tablaPersona  = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY," +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150));";
        this.conn.prepareStatement(tablaPersona).execute();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        return csvParser.getRecords();
    }

    public void importData(ClienteDAO clienteDAO) throws Exception {
        for(CSVRecord record : getData("clientes.csv")){
            int idCliente = Integer.parseInt(record.get(0));
            String nombre = record.get(1);
            String email = record.get(2);
            if(record.size() >= 3){
                clienteDAO.insertCliente(idCliente, nombre, email);
            }
        }
    }
}

package utils;

import DAO.ClienteDAO;
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

    public HelperMySQL(ClienteDAO clienteDAO) {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/arquiDB";
        this.clienteDAO = clienteDAO;

//        try {
//            Class.forName(driver).getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTable() throws SQLException {
        String sql = "DROP TABLE Cliente";
        this.conn.prepareStatement(sql).execute();
    }
    public void createTable() throws SQLException {
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

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
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

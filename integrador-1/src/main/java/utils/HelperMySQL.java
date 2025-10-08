package utils;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import entities.Cliente;
import entities.Factura;
import entities.Factura_Producto;
import entities.Producto;
import factory.MySQLFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {
        this.conn = MySQLFactory.createConnection();
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

    public void dropTables() {
        System.out.println("Eliminando tablas...");
        try{
            String dropFacturaProducto = "DROP TABLE Factura_Producto;";
            String dropCliente = "DROP TABLE Cliente";
            String dropProducto = "DROP TABLE Producto";
            String dropFactura = "DROP TABLE Factura";

            this.conn.prepareStatement(dropFacturaProducto).execute();
            this.conn.prepareStatement(dropFactura).execute();
            this.conn.prepareStatement(dropCliente).execute();
            this.conn.prepareStatement(dropProducto).execute();
            this.conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createTables() throws SQLException {
        System.out.println("Creando tablas...");
        String tablaCliente  = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY," +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150));";
        this.conn.prepareStatement(tablaCliente).execute();
        String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura (" +
                "idFactura INT PRIMARY KEY," +
                "idCliente INT," +
                "FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente));";
        this.conn.prepareStatement(tablaFactura).execute();
        String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT PRIMARY KEY," +
                "nombre VARCHAR(45)," +
                "valor FLOAT);";
        this.conn.prepareStatement(tablaProducto).execute();
        String tablaFacturaProducto = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT," +
                "idProducto INT," +
                "cantidad INT," +
                "PRIMARY KEY(idFactura,idProducto)," +
                "FOREIGN KEY(idProducto) REFERENCES Producto(idProducto)," +
                "FOREIGN KEY(idFactura) REFERENCES Factura(idFactura));";
        this.conn.prepareStatement(tablaFacturaProducto).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-1\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        return csvParser.getRecords();
    }

    public void importData(ClienteDAO cliente, ProductoDAO producto, FacturaDAO factura, Factura_ProductoDAO factura_producto) throws Exception {
        System.out.println("Cargando datos. La opeacion puede demorar.");
        for(CSVRecord record : getData("clientes.csv")){
            int idCliente = Integer.parseInt(record.get(0));
            String nombre = record.get(1);
            String email = record.get(2);
            if(record.size() >= 3){
                cliente.insert(new Cliente(idCliente, nombre, email));
            }
        }
        for(CSVRecord record : getData("productos.csv")){
            int idProducto = Integer.parseInt(record.get(0));
            String nombre = record.get(1);
            float valor = Float.parseFloat(record.get(2));
            if(record.size() >= 2){
                producto.insert(new Producto(idProducto, nombre, valor));
            }
        }
        for(CSVRecord record : getData("facturas.csv")){
            int idFactura = Integer.parseInt(record.get(0));
            int idCliente = Integer.parseInt(record.get(1));
            if(record.size() >= 2){
                factura.insert(new Factura(idFactura, idCliente));
            }
        }
        for(CSVRecord record : getData("facturas-productos.csv")){
            int idFactura = Integer.parseInt(record.get(0));
            int idProducto = Integer.parseInt(record.get(1));
            int cantidad = Integer.parseInt(record.get(2));
            if(record.size() >= 3){
                factura_producto.insert(new Factura_Producto(idFactura, idProducto, cantidad));
            }
        }
        System.out.println("Datos cargados correctamente.");
    }
}

package DAO;

import DTO.ProductoDTO;
import entities.Producto;

public interface ProductoDAO extends CRUD<Producto> {
    public ProductoDTO getProductoMayorRecaudacion();
}

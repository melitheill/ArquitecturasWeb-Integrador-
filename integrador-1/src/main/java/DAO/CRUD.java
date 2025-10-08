package DAO;

import java.sql.SQLException;

public interface CRUD<T> {
    public void insert(T entity) throws SQLException;
    public boolean delete(T entity) throws SQLException;
    public boolean update(T entity) throws SQLException;
    public T find(T entity) throws SQLException;
}

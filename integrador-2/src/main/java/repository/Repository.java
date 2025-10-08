package repository;

import entity.Identificador.Identificador;

public interface Repository<T> {
    T findById(Identificador id);
    void create(T elem);
    void delete(T elem);
    void update(T elem);

}

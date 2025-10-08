package entity.Identificador;

import java.io.Serializable;

public class Identificador implements Serializable {
    private int id;
    public Identificador() {
    }
    public Identificador(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}

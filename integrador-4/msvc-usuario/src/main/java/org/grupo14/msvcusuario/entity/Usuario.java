package org.grupo14.msvcusuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    private String mail;
    @JsonIgnore
    @DBRef
    private List<Cuenta> cuentas;
    private double latitud;
    private double longitud;
    private String tipoCuenta;


    public Usuario(String nombre, String mail, double latitud, double longitud, String tipoCuenta) {
        this.nombre = nombre;
        this.mail = mail;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipoCuenta = tipoCuenta;
        this.cuentas = new ArrayList<>();
    }
}

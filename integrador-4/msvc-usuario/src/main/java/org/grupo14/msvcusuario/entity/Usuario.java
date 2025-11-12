package org.grupo14.msvcusuario.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
//    private String rol;
    private String mail;
    @ManyToMany
    private List<Cuenta> cuentas;
    private double latitud;
    private double longitud;
//    private List<String> monopatines;


    public Usuario(String nombre, String mail, double latitud, double longitud) {
        this.nombre = nombre;
        this.mail = mail;
        this.latitud = latitud;
        this.longitud = longitud;
        this.cuentas = new ArrayList<>();
    }
}

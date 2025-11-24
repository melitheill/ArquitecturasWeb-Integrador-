package org.grupo14.msvcusuario.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String mail;
    private double latitud;
    private double longitud;
    private String tipoCuenta;
}

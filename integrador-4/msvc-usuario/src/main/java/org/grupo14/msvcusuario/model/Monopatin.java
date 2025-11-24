package org.grupo14.msvcusuario.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "monopatin")

public class Monopatin {
    @Id
    private Long id;
    private String estado;
    private double latitud;
    private double longitud;

}

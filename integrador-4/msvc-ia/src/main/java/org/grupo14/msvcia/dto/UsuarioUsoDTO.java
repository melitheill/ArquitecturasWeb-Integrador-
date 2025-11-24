package org.grupo14.msvcia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUsoDTO {
    private Long id;
    private UsoMonopatinesDTO usos;
}

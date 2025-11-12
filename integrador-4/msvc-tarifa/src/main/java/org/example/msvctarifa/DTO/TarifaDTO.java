package org.example.msvctarifa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifaDTO {
    private int montoBase;
    private int montoConPausa;

}

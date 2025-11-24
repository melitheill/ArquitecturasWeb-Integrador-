package org.grupo14.msvcpagomock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagoResponseDTO {

   private String estado;   // "APROBADO" / "RECHAZADO"
   private String mensaje;
}

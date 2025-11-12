package org.grupo14.msvcfactura.service;

import lombok.RequiredArgsConstructor;
import org.example.msvcparada.DTO.TarifaDTO;
import org.grupo14.msvcfactura.DTO.FacturaDTO;
import org.grupo14.msvcfactura.Model.Tarifa;
import org.grupo14.msvcfactura.entity.Factura;
import org.grupo14.msvcfactura.feignClients.TarifaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.grupo14.msvcfactura.repository.FacturaRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    private final TarifaFeignClient tarifaFeignClient;

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura delete(Factura factura) {
        facturaRepository.delete(factura);
        return factura;
    }

    public Factura update(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura facturar(FacturaDTO facturaDTO) {
        int km_recorridos = facturaDTO.getKm_recorridos();
        int km_recorridosConPausa = facturaDTO.getKm_recorridosPausaExtensa();

        Tarifa t= tarifaFeignClient.getTarifaActiva();
         int monto_km_recorridos = t.getMontoBase();
         int monto_km_recorridosConPausa = t.getMontoConPausa();

         int montoTotal=km_recorridos * monto_km_recorridos
                 + km_recorridosConPausa * monto_km_recorridosConPausa;
        return facturaRepository.save(new Factura(montoTotal,facturaDTO.getFecha()));
    }
    public Tarifa getTarifa(){
        return tarifaFeignClient.getTarifaActiva();
    }
    public int getTotalFacturadoEntre(int anio, int mesInicio, int mesFin) {

        int total=0;
        if (mesInicio>mesFin){
            total += facturaRepository.getTotalFacturadoEntre(anio ,mesFin,mesInicio);
        }else {
            total += facturaRepository.getTotalFacturadoEntre(anio, mesInicio, mesFin);
        }
        return total;
    }

}

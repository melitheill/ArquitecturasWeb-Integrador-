package org.example.msvccuenta.service;


import org.example.msvccuenta.entity.Cuenta;
import org.example.msvccuenta.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    public List<Cuenta> getAll(){
        return cuentaRepository.findAll();
    }

    public Cuenta save(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public Cuenta delete(Cuenta cuenta){
        cuentaRepository.delete(cuenta);
        return cuenta;
    }

    public Cuenta findById(Long id){
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta update(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }
}
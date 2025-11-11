package org.grupo14.msvcmonopatin.service;

import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.grupo14.msvcmonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    public List<Monopatin> findAll() {
        return monopatinRepository.findAll();
    }

    public Monopatin findById(long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public Monopatin save(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);

    }

    public Monopatin update(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public Monopatin delete(Monopatin monopatin) {
        monopatinRepository.delete(monopatin);
        return monopatin;
    }


}

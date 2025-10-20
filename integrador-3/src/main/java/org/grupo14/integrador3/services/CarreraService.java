package org.grupo14.integrador3.services;

import jakarta.transaction.Transactional;
import org.grupo14.integrador3.dto.CantidadPorAnioDTO;
import org.grupo14.integrador3.dto.CarreraDTO;
import org.grupo14.integrador3.dto.CarreraInfoDTO;
import org.grupo14.integrador3.dto.ReporteDTO;
import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.repository.CarreraRepository;
import org.grupo14.integrador3.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("CarreraService")
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;


    @Transactional
    public Iterable<Carrera> findAll(){
        return carreraRepository.findAll();
    }

    @Transactional
    public Optional<Carrera> findById(Integer idCarrera){
        return carreraRepository.findById(idCarrera);
    }

    @Transactional
    public List<CarreraDTO> obtenerCarrerasConEstudiantesInscriptos(){
        List<Object[]> carreras = carreraRepository.obtenerCarrerasConEstudiantesInscriptos();
        List<CarreraDTO> carreraDTOS = new ArrayList<>();
        for (Object[] elemento : carreras) {
            String carrera = (String) elemento[0];
            int cantidad = Math.toIntExact((Long) elemento[1]);
            CarreraDTO carreraDTO = new CarreraDTO(carrera, cantidad);
            carreraDTOS.add(carreraDTO);
        }
        return carreraDTOS;
    }

    @Transactional
    public Carrera save(Carrera carrera) throws Exception {
        Optional<Carrera> c = findById(carrera.getId());
        if (c.isPresent()) {
            throw new Exception("La carrera ya existe");
        }
        return carreraRepository.save(carrera);
    }

    @Transactional
    public Carrera update(int idCarrera,Carrera carrera) throws Exception {
        Optional<Carrera> entity = findById(idCarrera);
        if(entity.isPresent()){
            return carreraRepository.save(carrera);
        }else {
            throw new Exception("La carrera no existe");
        }
    }

    @Transactional
    public void delete(int idCarrera) throws Exception {
        Optional<Carrera> entity = findById(idCarrera);
        if(entity.isPresent()){
            carreraRepository.delete(entity.get());
        } else {
            throw new Exception("La carrera no existe");
        }
    }

    public List<ReporteDTO> getReporte(){
        List<Carrera> carreras = carreraRepository.findAll(Sort.by(Sort.Direction.ASC, "carrera"));
        List<ReporteDTO> reportesDTO = new ArrayList<>();
        for(Carrera carrera : carreras){
            ReporteDTO reporteDTO = new ReporteDTO(carrera.getCarrera());

            List<CantidadPorAnioDTO> inscriptos = estudianteCarreraRepository.getCarreraByInscripcion(carrera);
            List<CantidadPorAnioDTO> egresados = estudianteCarreraRepository.getCarreraByGraduacion(carrera);

            for(CantidadPorAnioDTO inscripto : inscriptos){
                reporteDTO.getInfoAnual().put(inscripto.getAnio(), new CarreraInfoDTO(inscripto.getCantidad()));
            }
            for(CantidadPorAnioDTO egresado : egresados){
                CarreraInfoDTO carreraInfoDTO = reporteDTO.getInfoAnual().get(egresado.getAnio());
                if(carreraInfoDTO == null){
                    carreraInfoDTO = new CarreraInfoDTO(0);
                }
                carreraInfoDTO.setEgresados(egresado.getCantidad());
                reporteDTO.getInfoAnual().put(egresado.getAnio(), carreraInfoDTO);
            }
            reportesDTO.add(reporteDTO);
        }
        return reportesDTO;
    }
}

package com.example.demo.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repositorio.CitaRepository;
import com.example.demo.Entidad.Cita;
import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public List<Cita> obtenerCitasPorMascota(Long idMascota) {
        return citaRepository.findByMascota_IdMascota(idMascota);
    }
}
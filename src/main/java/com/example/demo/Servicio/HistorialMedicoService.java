package com.example.demo.Servicio;

import com.example.demo.Entidad.HistorialMedico;
import com.example.demo.Entidad.Mascota;
import com.example.demo.Repositorio.HistorialMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialMedicoService {
    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    public HistorialMedico guardarHistorial(HistorialMedico historial) {
        return historialMedicoRepository.save(historial);
    }

    public List<HistorialMedico> listarHistoriales() {
        return historialMedicoRepository.findAll();
    }

    public List<HistorialMedico> buscarHistorialMedicoNombre(String buscarHistorialMedico){
        if(buscarHistorialMedico == null || buscarHistorialMedico.isEmpty()){
            return historialMedicoRepository.findAll();
        } else {
            return historialMedicoRepository.findByDescripcionContainingIgnoreCase(buscarHistorialMedico);
        }
    }
    public List<HistorialMedico> obtenerTodos() {
        return historialMedicoRepository.findAll();
    }
}

package com.example.demo.Servicio;

import com.example.demo.Entidad.Cita;
import com.example.demo.Entidad.Veterinario;
import com.example.demo.Repositorio.CitaRepository;
import com.example.demo.Repositorio.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    @Autowired
    private CitaRepository citaRepository;

    public Veterinario guardarVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public List<Veterinario> listarVeterinarios() {
        return veterinarioRepository.findAll();
    }

    public List<Cita> obtenerCitasPorVeterinario(Long idVeterinario) {
        return citaRepository.findByVeterinario_IdVeterinario(idVeterinario);
    }
}

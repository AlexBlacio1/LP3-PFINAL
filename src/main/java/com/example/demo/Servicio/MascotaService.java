package com.example.demo.Servicio;

import com.example.demo.Entidad.Cita;
import com.example.demo.Entidad.HistorialMedico;
import com.example.demo.Entidad.Mascota;
import com.example.demo.Entidad.Usuario;
import com.example.demo.Repositorio.MascotaRepository;
import com.example.demo.Repositorio.CitaRepository;  // Necesitas importar este repositorio
import com.example.demo.Repositorio.HistorialMedicoRepository;  // Necesitas importar este repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }
    public Optional<Mascota> buscarMascota(Long id){
        return mascotaRepository.findById(id);
    }

    public List<Cita> obtenerCitasPorMascota(Long id) {
        return citaRepository.findByMascota_IdMascota(id);
    }

    public HistorialMedico obtenerHistorialPorMascota(Long idMascota) {
        return historialMedicoRepository.findByMascota_IdMascota(idMascota).stream().findFirst().orElse(null);
    }


    public List<Mascota> obtenerMascotasPorUsuario(Long idUsuario) {
        return mascotaRepository.findByUsuario_IdUsuario(idUsuario);
    }
    public List<Mascota> buscarMascotaNombre(String buscarMascota){
        if(buscarMascota == null || buscarMascota.isEmpty()){
            return mascotaRepository.findAll();
        } else {
            return mascotaRepository.findByNombreContainingIgnoreCase(buscarMascota);
        }
    }
    public void eliminarMascota(Long idMascota) {
        historialMedicoRepository.deleteByMascota_IdMascota(idMascota);

        mascotaRepository.deleteById(idMascota);
    }
}

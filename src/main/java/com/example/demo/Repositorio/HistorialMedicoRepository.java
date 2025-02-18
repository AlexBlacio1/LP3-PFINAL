package com.example.demo.Repositorio;

import com.example.demo.Entidad.HistorialMedico;
import com.example.demo.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
    List<HistorialMedico> findByMascota_IdMascota(Long idMascota);

    void deleteByMascota_IdMascota(Long idMascota);
    List<HistorialMedico> findByDescripcionContainingIgnoreCase(String buscarHistorialMedico);
}

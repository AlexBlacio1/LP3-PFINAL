package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entidad.Cita;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMascota_IdMascota(Long idMascota);
    List<Cita> findByVeterinario_IdVeterinario(Long idVeterinario);
}
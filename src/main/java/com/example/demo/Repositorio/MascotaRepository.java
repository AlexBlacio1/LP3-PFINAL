package com.example.demo.Repositorio;

import com.example.demo.Entidad.Mascota;
import com.example.demo.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    public List<Mascota> findByUsuario_IdUsuario(Long id);
    List<Mascota> findByNombreContainingIgnoreCase(String buscarMascota);
}

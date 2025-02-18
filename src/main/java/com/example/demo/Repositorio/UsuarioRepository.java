package com.example.demo.Repositorio;

import com.example.demo.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreContainingIgnoreCase(String buscarUsuario);
    Usuario findByCorreo(String correo);
    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    Usuario buscarUsuarioByCorreo(@Param("correo") String correo);
}

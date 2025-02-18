package com.example.demo.Entidad;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;
    private String descripcion;
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;
}

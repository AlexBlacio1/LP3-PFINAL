package com.example.demo.Entidad;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeterinario;
    private String nombre;
    private String especialidad;
    private String telefono;

    @OneToMany(mappedBy = "veterinario")
    private List<Cita> citas;
}

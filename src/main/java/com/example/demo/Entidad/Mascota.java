package com.example.demo.Entidad;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "mascota")
    private List<Cita> citas;

    @OneToMany(mappedBy = "mascota")
    private List<HistorialMedico> historialMedico;
}

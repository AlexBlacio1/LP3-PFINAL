package com.example.demo.Controlador;

import com.example.demo.Entidad.Cita;
import com.example.demo.Entidad.Veterinario;
import com.example.demo.Servicio.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping
    public Veterinario crearVeterinario(@RequestBody Veterinario veterinario) {
        return veterinarioService.guardarVeterinario(veterinario);
    }

    @GetMapping("/veterinarios/{id}/citas")
    public List<Cita> obtenerCitasPorVeterinario(@PathVariable Long id) {
        return veterinarioService.obtenerCitasPorVeterinario(id);
    }
}
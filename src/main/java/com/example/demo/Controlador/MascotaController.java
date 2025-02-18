package com.example.demo.Controlador;


import com.example.demo.Entidad.Cita;
import com.example.demo.Entidad.HistorialMedico;
import com.example.demo.Entidad.Mascota;
import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/mascotas")
    public String mostrarMascota(@RequestParam(name = "buscarmascota", required = false, defaultValue = "") String buscarMascota, Model model) {
        List<Mascota> mascotas = mascotaService.buscarMascotaNombre(buscarMascota);
        model.addAttribute("buscarMascota", buscarMascota);
        model.addAttribute("mascotas", mascotas);
        return "Mascota/vistaMascota";
    }

    @GetMapping("/newMascota")
    public String mostrarFormularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "Mascota/formMascota";
    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaService.guardarMascota(mascota);
    }

    @GetMapping("/mascotas/{id}/citas")
    public List<Cita> obtenerCitasPorMascota(@PathVariable Long id) {
        return mascotaService.obtenerCitasPorMascota(id);
    }

    @GetMapping("/mascotas/{id}/historial")
    public HistorialMedico obtenerHistorialPorMascota(@PathVariable Long id) {
        return mascotaService.obtenerHistorialPorMascota(id);
    }

    @GetMapping("/mascotas/nuevo")
    public String formularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "Mascota/formMascota";
    }

    @PostMapping("/mascotas/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota) {
        if (mascota.getIdMascota() != null) {
            mascotaService.guardarMascota(mascota);
        } else {
            mascotaService.guardarMascota(mascota);
        }
        return "redirect:/mascotas";
    }


    @GetMapping("/mascotas/editar/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaService.buscarMascota(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "Mascota/formMascota";
        } else {
            return "redirect:/mascotas";
        }
    }
    @GetMapping("/mascotas/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/mascotas";
    }
}

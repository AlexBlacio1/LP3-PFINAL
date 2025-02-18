package com.example.demo.Controlador;

import com.example.demo.Entidad.HistorialMedico;
import com.example.demo.Servicio.HistorialMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/historialMedico")
public class HistorialMedicoController {
    @Autowired
    private HistorialMedicoService historialMedicoService;

    @GetMapping("/listar")
    public String listarHistorialMedico(Model model) {
        List<HistorialMedico> historialMedicoList = historialMedicoService.obtenerTodos();
        model.addAttribute("historialMedico", historialMedicoList);
        return "Historial/vistaHistorial";
    }

    @GetMapping("/historial")
    public String mostrarHistorialMedico(@RequestParam(name = "buscarhistorialMedico", required = false, defaultValue = "") String buscarHistorialMedico, Model model) {
        List<HistorialMedico> historialMedicos = historialMedicoService.buscarHistorialMedicoNombre(buscarHistorialMedico);
        model.addAttribute("buscarHistorialMedico", buscarHistorialMedico);
        model.addAttribute("historialMedicos", historialMedicos);
        return "Historial/vistaHistorial";
    }

    @GetMapping
    public String mostrarPaginaPrincipal() {
        return "redirect:/historialMedico/listar"; // O redirige a la página de listado si prefieres
    }

    @PostMapping
    public HistorialMedico crearHistorial(@RequestBody HistorialMedico historialMedico) {
        return historialMedicoService.guardarHistorial(historialMedico);
    }
}

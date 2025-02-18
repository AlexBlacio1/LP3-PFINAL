package com.example.demo.Controlador;

import com.example.demo.Entidad.Mascota;
import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.MascotaService;
import com.example.demo.Servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/usuarios")
    public String mostrarUsuario(@RequestParam(name = "buscarUsuario", required = false, defaultValue = "") String buscarUsuario, Model model) {
        List<Usuario> usuarios = usuarioService.buscarUsuarioNombre(buscarUsuario);
        model.addAttribute("buscarUsuario", buscarUsuario);
        model.addAttribute("usuarios", usuarios);
        return "Usuario/vistaUsuario";
    }


    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        if (usuario.getIdUsuario() != null) {
            usuarioService.guardarUsuario(usuario);
        } else {
            usuarioService.guardarUsuario(usuario);
        }
        return "redirect:/usuarios";
    }


    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "Usuario/formUsuario";
        } else {
            return "redirect:/usuarios";
        }
    }
    @GetMapping("/formUsuario")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Usuario/formUsuario";
    }


    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    @GetMapping("/{id}/mascotas")
    public String obtenerMascotasPorUsuario(@PathVariable Long id, Model model) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasPorUsuario(id);
        model.addAttribute("mascotas", mascotas);
        return "Mascota/vistaMascota";
    }
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario.getNombre(), usuario.getCorreo(),usuario.getTelefono(), usuario.getContrasena());
            model.addAttribute("exito", "Usuario registrado exitosamente");
            return "index";
        } catch (Exception e) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("error", e.getMessage());
            return "Usuario/formUsuario";
        }
    }

}
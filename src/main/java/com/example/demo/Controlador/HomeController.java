package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/registrar")
    public String formularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Usuario/formUsuario";
    }
    @GetMapping("/login")
    public String login() {
        return "Usuario/login";
    }
    @GetMapping("/inicio")
    public String inicio() {
        return "Usuario/inicioSesion";
    }
    @GetMapping("/servicios")
    public String service() {
        return "Usuario/pageServicio";
    }
}

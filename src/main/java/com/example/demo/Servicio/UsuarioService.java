package com.example.demo.Servicio;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Repositorio.UsuarioRepository;
import com.example.demo.Roles.Rol;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Setter
@Getter
@Service
public class UsuarioService implements UserDetailsService  {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registrarUsuario(String nombre, String correo,String telefono, String contrasena) {

        Optional<Usuario> usuarioExistente = Optional.ofNullable(usuarioRepository.findByCorreo(correo));

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(nombre);
            usuario.setContrasena(passwordEncoder.encode(contrasena));
            usuario.setTelefono(telefono);
            usuario.setRol(Rol.USER);
            usuarioRepository.save(usuario);
        } else {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setContrasena(passwordEncoder.encode(contrasena));
            usuario.setTelefono(telefono);
            usuario.setRol(Rol.USER);
            usuarioRepository.save(usuario);
        }
    }




    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuario(Long id){
        return usuarioRepository.findById(id);
    }

   public void guardarUsuario(Usuario usuario){usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarUsuarioNombre(String buscarUsuario){
        if(buscarUsuario == null || buscarUsuario.isEmpty()){
            return usuarioRepository.findAll();
        } else {
            return usuarioRepository.findByNombreContainingIgnoreCase(buscarUsuario);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                AuthorityUtils.createAuthorityList("ROLE_" + usuario.getRol().toString())
        );
    }}
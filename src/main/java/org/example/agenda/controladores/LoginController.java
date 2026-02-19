package org.example.agenda.controladores;

import org.apache.coyote.BadRequestException;
import org.example.agenda.entidades.Usuario;
import org.example.agenda.repositorios.UsuarioRepository;
import org.example.agenda.seguridad.Constans;
import org.example.agenda.seguridad.JWTAuthenticationConfig;
import org.example.agenda.seguridad.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;
    @Autowired
    UsuarioRepository usuarioRepository;
    @PostMapping("login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass)
            throws BadRequestException {
        List<Usuario> usuarios = usuarioRepository.getUsuarios();
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) &&
                    PasswordEncryptor.decrypt(usuario.getEncryptedPass()).equals(encryptedPass)) {
                usuarioEncontrado = usuario;
                break;
            }
        }
        if (usuarioEncontrado == null) {
            throw new BadRequestException();
        }
        String token =
                jwtAuthtenticationConfig.getJWTToken(usuarioEncontrado.getUsername(), usuarioEncontrado.getRol());
        return token;
    }
}
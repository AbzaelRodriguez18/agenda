package org.example.agenda.repositorios;

import org.example.agenda.entidades.Rol;
import org.example.agenda.entidades.Usuario;
import org.example.agenda.seguridad.PasswordEncryptor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public List<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("aitor",
                PasswordEncryptor.encrypt("1234"), Rol.ADMIN));
        usuarios.add(new Usuario("alicia",
                PasswordEncryptor.encrypt("1111"), Rol.USER));
        usuarios.add(new Usuario("pedro",
                PasswordEncryptor.encrypt("2222"), Rol.READER));
        return usuarios;
    }
}
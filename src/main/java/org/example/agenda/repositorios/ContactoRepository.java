package org.example.agenda.repositorios;

import org.example.agenda.entidades.Contacto;

import java.util.List;

public interface ContactoRepository {
    List<Contacto> obtenerTodos();
    Contacto obtenerPorId(Long id);
    Contacto guardar(Contacto contacto);
    void eliminar(Long id);
    Contacto actualizar(Long id,Contacto contacto);
}

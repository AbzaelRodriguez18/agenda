package org.example.agenda.servicios;

import org.example.agenda.entidades.Contacto;
import org.example.agenda.repositorios.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService {
    private final ContactoRepository contactoRepository;
    @Autowired
    public ContactoServiceImpl(ContactoRepository contactoRepository)
    {
        this.contactoRepository = contactoRepository;
    }
    @Override
    public List<Contacto> obtenerTodos() {
        return contactoRepository.obtenerTodos();
    }
    @Override
    public Contacto obtenerPorId(Long id) {
        return contactoRepository.obtenerPorId(id);
    }
    @Override
    public Contacto guardar(Contacto contacto) {
        return contactoRepository.guardar(contacto);
    }
    @Override
    public void eliminar(Long id) {
        contactoRepository.eliminar(id);
    }
    @Override
    public Contacto actualizar(Long id,Contacto contactoNuevo) {
        Contacto contactoViejo = obtenerPorId(id);
        if (contactoViejo != null){
            contactoViejo.setNombre(contactoNuevo.getNombre());
            contactoViejo.setTelefono(contactoNuevo.getTelefono());
        }
        return contactoViejo;
    }
}
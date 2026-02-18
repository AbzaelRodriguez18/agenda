package org.example.agenda;

import org.example.agenda.entidades.Contacto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ClientePrueba {
    private static final String BASE_URL =
            "http://localhost:8080/contactos";
    public static void main(String[] args) {

        Contacto nuevoContacto = new Contacto("Juan", "987654321");

        ClientePrueba cliente = new ClientePrueba();
        cliente.realizarPruebas(nuevoContacto);
    }
    public void realizarPruebas(Contacto nuevoContacto) {

        nuevoContacto = agregarContacto(nuevoContacto);
        System.out.println("Contacto agregado: " + nuevoContacto);

        listarContactos();

        obtenerContactoPorId(nuevoContacto.getId());
        eliminarContacto(nuevoContacto.getId());

        listarContactos();

        Contacto contacto = new Contacto("Abzael Rodriguez","637161819");

        nuevoContacto = modificarContacto(Long.valueOf(2),contacto);

        System.out.println("Contacto modificado: " + nuevoContacto);


        listarContactos();

        eliminarContacto(nuevoContacto.getId());

    }
    private Contacto agregarContacto(Contacto contacto) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contacto> response =
                restTemplate.postForEntity(BASE_URL, contacto, Contacto.class);
        return response.getBody();
    }
    private void listarContactos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contacto[]> response =
                restTemplate.getForEntity(BASE_URL, Contacto[].class);
        Contacto[] contactos = response.getBody();
        System.out.println("Lista de contactos:");
        for (Contacto contacto : contactos) {
            System.out.println(contacto);
        }
    }
    private void obtenerContactoPorId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Contacto contacto = restTemplate.getForObject(BASE_URL + "/"
                + id, Contacto.class);
        System.out.println("Contacto obtenido por ID: " + contacto);
    }
    private void eliminarContacto(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Contacto eliminado con ID: " + id);
    }
    private Contacto modificarContacto(Long id, Contacto contacto)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Contacto> requestEntity = new
                HttpEntity<>(contacto, headers);
        ResponseEntity<Contacto> response =
                restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT,
                        requestEntity, Contacto.class);
        return response.getBody();
    }
}
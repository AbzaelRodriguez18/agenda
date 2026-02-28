package org.example.agenda.entidades;

public enum Rol {
    ADMIN("ADMIN"),
    USER("USER"),
    READER("READER");

    private final String rol;

    Rol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
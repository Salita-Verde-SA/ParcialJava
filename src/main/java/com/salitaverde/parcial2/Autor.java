package com.salitaverde.parcial2;

public class Autor extends Persona {
    public String pseudonimo;

    public Autor(int dni, String nombre, String pseudonimo) {
        super(dni, nombre);
        this.pseudonimo = pseudonimo;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

}

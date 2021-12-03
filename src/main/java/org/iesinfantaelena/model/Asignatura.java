package org.iesinfantaelena.model;

import java.util.Objects;

public class Asignatura {

    private int identificador;
    private String nombre;
    private String tipo;
    private float creditos;
    private boolean superada = false;

    public Asignatura(int identificador, String tipo, String nombre, float creditos) { //Constructor para BD
        this.identificador = identificador;
        this.nombre = nombre;
        this.tipo = tipo;
        this.creditos = creditos;
    }
    //Constructor cuando la contine un alumno
    public Asignatura(int identificador, String tipo, String nombre, float creditos, boolean superada) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.tipo = tipo;
        this.creditos = creditos;
        this.superada = superada;
    }


    public Asignatura() {
    }
    //*******Equals hash code************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return identificador == that.identificador && Float.compare(that.creditos, creditos) == 0 && superada == that.superada && Objects.equals(nombre, that.nombre) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, nombre, tipo, creditos, superada);
    }
    //*******Getters y setters********
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public boolean isSuperada() {
        return superada;
    }

    public void setSuperada(boolean superada) {
        this.superada = superada;
    }
}

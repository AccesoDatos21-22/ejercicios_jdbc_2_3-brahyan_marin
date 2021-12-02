package org.iesinfantaelena.model;

import java.util.Objects;

public class Alumno {

    private String nombre;
    private int id;
    private String apellidos;
    private int curso;
    private int titulacion;

    public Alumno(int id,String apellidos,String nombre, int curso, int titulacion) {
        this.nombre = nombre;
        this.id = id;
        this.apellidos = apellidos;
        this.curso = curso;
        this.titulacion = titulacion;
    }

    public Alumno() {
    }
    //***** equals hashcode *****
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return id == alumno.id && curso == alumno.curso && titulacion == alumno.titulacion && Objects.equals(nombre, alumno.nombre) && Objects.equals(apellidos, alumno.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id, apellidos, curso, titulacion);
    }
    //***** Getters y Setters *****
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(int titulacion) {
        this.titulacion = titulacion;
    }
}

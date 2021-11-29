package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Alumno;
import org.iesinfantaelena.model.Asignatura;

import java.util.List;

public class JDBCAlumnoDAO implements AlumnoDAO{
    @Override
    public List<Alumno> buscar(String nombre) throws MatriculaException {
        return null;
    }

    @Override
    public Alumno buscar(int id) throws MatriculaException {
        return null;
    }

    @Override
    public void insertar(Alumno alumno) throws MatriculaException {

    }

    @Override
    public void borrar(int id) throws MatriculaException {

    }

    @Override
    public void matricular(Alumno alumno, Asignatura asignatura) throws MatriculaException {

    }

    @Override
    public void cerrar() {

    }

    @Override
    public void liberar() {

    }
}

package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Asignatura;

import java.util.List;

public class JDBCAsignaturaDAO implements AsignaturaDAO{

    public JDBCAsignaturaDAO(){
        
    }
    @Override
    public List<Asignatura> buscar(String nombre) throws MatriculaException {
        return null;
    }

    @Override
    public Asignatura buscar(int identificador) throws MatriculaException {
        return null;
    }

    @Override
    public void insertar(Asignatura asignatura) throws MatriculaException {

    }

    @Override
    public void borrar(Asignatura asignatura) throws MatriculaException {

    }

    @Override
    public void cerrar() {

    }

    @Override
    public void liberar() {

    }
}

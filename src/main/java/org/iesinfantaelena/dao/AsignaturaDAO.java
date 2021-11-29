package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Asignatura;

import java.util.List;

public interface AsignaturaDAO {

    /**
     *
     * Metodo que busca una asignatura por nombre
     *
     * @throws MatriculaException
     */

    public List<Asignatura> buscar(String nombre) throws MatriculaException;
    /**
     *
     * Metodo que busca un Asignatura por identificador
     *
     * @param identificador
     * @throws MatriculaException
     */

    public Asignatura buscar(int identificador) throws MatriculaException;

    /**
     * Método para insertar una fila
     *
     * @param asignatura
     * @throws MatriculaException
     */
    public void insertar(Asignatura asignatura) throws MatriculaException;

    /**
     * Método para borrar una fila dada un asignatura
     *
     * @param asignatura
     * @return
     */
    public void borrar(Asignatura asignatura) throws MatriculaException;


    public void cerrar();

    /**
     * Método para liberar recursos del DAO
     *
     */
    public void liberar();
}


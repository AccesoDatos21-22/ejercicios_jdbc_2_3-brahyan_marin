package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Alumno;
import org.iesinfantaelena.model.Asignatura;

import java.util.List;

public interface AlumnoDAO {

    /**
     *
     * Metodo que busca un alumno por nombre
     *
     * @throws MatriculaException
     */

    public List<Alumno> buscar(String nombre) throws MatriculaException;
    /**
     *
     * Metodo que busca un alumno por id
     *
     * @param id
     * @throws MatriculaException
     */

    public Alumno buscar(int id) throws MatriculaException;

    /**
     * Método para insertar una fila
     *
     * @param alumno
     * @throws MatriculaException
     */
    public void insertar(Alumno alumno) throws MatriculaException;

    /**
     * Método para borrar una fila dado un alumno
     *
     * @param id
     * @return
     */
    public void borrar(int id) throws MatriculaException;

    /**
     * Método para matricular a un alumno en asignaturas
     *
     * @param alumno
     * @param asignatura
     * @throws MatriculaException
     */
    public void matricular(Alumno alumno, Asignatura asignatura) throws MatriculaException;

    /**
     * Método para cerrar el DAO
     *
     */
    public void cerrar();

    /**
     * Método para liberar recursos del DAO
     *
     */
    public void liberar();
}



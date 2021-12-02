package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Asignatura;
import org.iesinfantaelena.utils.Utilidades;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class JDBCAsignaturaDAO implements AsignaturaDAO{


    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public JDBCAsignaturaDAO() throws AccesoDatosException{
        try {
            con = new Utilidades().getConnection();
        } catch (SQLException e) {
            Utilidades.printSQLException(e);
            throw new AccesoDatosException("Error en el establecicimiento de la conexion");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosException("Error en la lectura del fichero de propiedades");
        }
        liberar();
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

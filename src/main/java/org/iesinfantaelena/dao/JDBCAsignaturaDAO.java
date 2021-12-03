package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Asignatura;
import org.iesinfantaelena.utils.Utilidades;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAsignaturaDAO implements AsignaturaDAO{

    //Fichero configuracion mariadb
    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + "/src/main/resources/mariadb-properties.xml";
    //Fichero creacion se BD matricula y sus diferentes tablas
    private static final String SQL_COMMANDS_FILE = System.getProperty("user.dir") + "/src/main/resources/alumnos-asignaturas.sql";
    //Queries
    private static final String SEARCH_ASIGNATURA_BYNOMBRE_QUERY = "select * from asignaturas where nombre=?";
    private static final String SEARCH_ASIGNATURA_BYID_QUERY = "select * from asignaturas where id_asignatura=?";
    private static final String DELETE_ASIGNATURA_QUERY = "delete from asignaturas where id_asignatura=?";
    private static final String INSERT_ASIGNATURA_QUERY = "insert into asignaturas values(?,?,?,?)";
    //Nombres columnas
    private static final String ID = "id_asignatura";//Integer
    private static final String TIPO = "tipo";//Varchar(2)
    private static final String NOMBRE = "nombre";//Varchar(60)
    private static final String CREDITOS = "creditos";//Float
    //----Objetos acceso a BD
    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public JDBCAsignaturaDAO() throws AccesoDatosException{
        try {
            //Se establece conexin con la base de datos
            con = new Utilidades(PROPERTIES_FILE).getConnection();
            //Se ejecuta el fichero con comandos SQL
            for(String comand: Utilidades.getSqlsCommandsFromFile(new File(SQL_COMMANDS_FILE))){
                stmt = con.createStatement();
                stmt.execute(comand);
                stmt = null;
            }
        } catch (SQLException sqle) {
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Error en el establecicimiento de la conexion");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosException("Error en la lectura del fichero de propiedades");
        }
        liberar();
    }
    @Override
    public List<Asignatura> buscar(String nombre) throws MatriculaException {
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        try{
            //Preparacion consulta
            pstmt = con.prepareStatement(SEARCH_ASIGNATURA_BYNOMBRE_QUERY);
            pstmt.setString(1,nombre);
            //Ejecucion consulta
            rs = pstmt.executeQuery();
            //Iteracion de los resultados y guardado en la lista los objetos asignatura
            while(rs.next()){
                int id = rs.getInt(ID);
                String tipo = rs.getString(TIPO);
                String nombreAsignatura = rs.getString(NOMBRE);
                float creditos = rs.getFloat(CREDITOS);
                listaAsignaturas.add(new Asignatura(id,tipo,nombreAsignatura,creditos));
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("Error en la busqueda de asignaturas con nombre: "+nombre);
        }finally {
            liberar();
        }
        return listaAsignaturas;
    }

    @Override
    public Asignatura buscar(int identificador) throws MatriculaException {
        Asignatura asignaturaBuscada = null;
        try{
            //Consulta
            pstmt = con.prepareStatement(SEARCH_ASIGNATURA_BYID_QUERY);
            pstmt.setInt(1,identificador);
            //Ejecucion
            rs = pstmt.executeQuery();
            //Ontencion de Asignatura a partir de resultados
            while(rs.next()) {
                int id = rs.getInt(ID);
                String tipo = rs.getString(TIPO);
                String nombre = rs.getString(NOMBRE);
                float creditos = rs.getFloat(CREDITOS);
                asignaturaBuscada = new Asignatura(id,tipo,nombre,creditos);
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("");
        }finally {
            liberar();
        }
        //Sera null si no se encuentra en la BD
        return asignaturaBuscada;
    }

    @Override
    public void insertar(Asignatura asignatura) throws MatriculaException {

        try{
            //Preparacion de los datos
            pstmt = con.prepareStatement(INSERT_ASIGNATURA_QUERY);
            pstmt.setInt(1,asignatura.getIdentificador());
            pstmt.setString(2,asignatura.getTipo());
            pstmt.setString(3,asignatura.getNombre());
            pstmt.setFloat(4,asignatura.getCreditos());
            //Ejecucion de la insercion
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("La asignatura con id: "+asignatura.getIdentificador()+" fue INSERTADA");
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("");
        }finally {
            liberar();
        }
    }

    @Override
    public void borrar(Asignatura asignatura) throws MatriculaException {

        try{
            //Preparacion sentencia
            pstmt = con.prepareStatement(DELETE_ASIGNATURA_QUERY);
            pstmt.setInt(1,asignatura.getIdentificador());
            //Ejecucion del borrado
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("La asignatura con id: "+asignatura.getIdentificador()+" fue BORRADA");
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("");
        }finally {
            liberar();
        }
    }

    @Override
    public void cerrar() {
        Utilidades.closeConnection(this.con);
    }

    @Override
    public void liberar() {
        try {
            if (this.rs != null) {
                this.rs.close();
                this.rs = null;
            }
            if (this.pstmt != null) {
                this.pstmt.close();
                this.pstmt = null;
            }
            if (this.stmt != null) {
                this.stmt.close();
                this.stmt = null;
            }
        } catch (SQLException e) {
            Utilidades.printSQLException(e);
        }
    }
}

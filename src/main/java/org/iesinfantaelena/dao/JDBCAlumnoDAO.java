package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Alumno;
import org.iesinfantaelena.model.Asignatura;
import org.iesinfantaelena.utils.Utilidades;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAlumnoDAO implements AlumnoDAO{

    //Fichero configuracion mariadb
    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + "/src/main/resources/mariadb-properties.xml";
    //Fichero creacion se BD matricula y sus diferentes tablas
    private static final String SQL_COMMANDS_FILE = System.getProperty("user.dir") + "/src/main/resources/alumnos-asignaturas.sql";
    //Queries
    private static final String SEARCH_ALUMNO_BYNAME_QUERY = "select * from alumnos where nombre=?";
    private static final String SEARCH_ALUMNO_BYID_QUERY = "select * from alumnos where id_alumno=?";
    private static final String INSERT_ALUMNO_QUERY = "insert into alumnos values(?,?,?,?,?)";
    private static final String DELETE_ALUMNO_QUERY = "delete from alumnos where id_alumno=?";
    private static final String UPDATE_ALUMNO_QUERY = "update alumnos set nombre=?,apellidos=?,curso=?,titulacion=? where id_alumno=?";
    private static final String MATRICULAR_ALUMNO_QUERY = "insert into alumnos_asignaturas values(?,?,?)";
    //Nombres columnas
    private static final String NOMBRE = "nombre"; //Varchar
    private static final String APELLIDOS = "apellidos"; //Varchar
    private static final String ID = "id_alumno";   //Integer PK
    private static final String CURSO = "curso";    //Interger
    private static final String TITULACION = "titulacion"; //Interger
    //----Objetos acceso a BD
    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public JDBCAlumnoDAO() throws AccesoDatosException {
        try {
            //Se establece conexin con la base de datos
            con = new Utilidades(PROPERTIES_FILE).getConnection();
            //Se ejecuta el fichero con comandos SQL
            for(String comando: Utilidades.getSqlsCommandsFromFile(new File(SQL_COMMANDS_FILE))){
                stmt = con.createStatement();
                stmt.execute(comando);
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
    public List<Alumno> buscar(String nombre) throws MatriculaException {

        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        try{
            //Preparamos consulta
            pstmt = con.prepareStatement(SEARCH_ALUMNO_BYNAME_QUERY);
            pstmt.setString(1,nombre);
            //obtenemos resultado
            rs = pstmt.executeQuery();
            while (rs.next()){
                //guadamos cada resultado en un objeto y a la lista
                int id = rs.getInt(ID);
                String nombreAlumno = rs.getString(NOMBRE);
                String apellidoAlumno = rs.getString(APELLIDOS);
                int curso = rs.getInt(CURSO);
                int titulacion = rs.getInt(TITULACION);
                listaAlumnos.add(new Alumno(id,apellidoAlumno,nombreAlumno,curso,titulacion));
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("Error en la busqueda de alumnos con nombre: "+nombre);
        }finally {
            liberar();
        }
        return listaAlumnos;
    }

    @Override
    public Alumno buscar(int id) throws MatriculaException {
        Alumno alumnoBuscado = null;
        try{
            pstmt = con.prepareStatement(SEARCH_ALUMNO_BYID_QUERY);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                //guadamos cada resultado en un objeto y a la lista
                int id_alumno = rs.getInt(ID);
                String nombreAlumno = rs.getString(NOMBRE);
                String apellidoAlumno = rs.getString(APELLIDOS);
                int curso = rs.getInt(CURSO);
                int titulacion = rs.getInt(TITULACION);
                alumnoBuscado = new Alumno(id_alumno,apellidoAlumno,nombreAlumno,curso,titulacion);
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("Error en la busqueda del alumno con ID: "+id);
        }finally {
            liberar();
        }

        return alumnoBuscado;
    }

    @Override
    public void insertar(Alumno alumno) throws MatriculaException {

        try{
            //Preparada insercion
            pstmt = con.prepareStatement(INSERT_ALUMNO_QUERY);
            pstmt.setInt(1,alumno.getId());
            pstmt.setString(2,alumno.getApellidos());
            pstmt.setString(3,alumno.getNombre());
            pstmt.setInt(4,alumno.getCurso());
            pstmt.setInt(5,alumno.getTitulacion());
            //Ejecucion de la insercion
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("alumno con id: "+alumno.getId()+" INSERTADO con exito");
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("Error al INSERTAR al alumno con id: "+alumno.getId());
        }finally {
            liberar();
        }
    }

    @Override
    public void borrar(int id) throws MatriculaException {

        try{
            pstmt = con.prepareStatement(DELETE_ALUMNO_QUERY);
            pstmt.setInt(1,id);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Alumno con id: "+id+" BORRADO");
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("Error al BORRAR al alumno con id: "+id);
        }finally {
            liberar();
        }
    }

    @Override
    public void matricular(Alumno alumno, Asignatura asignatura) throws MatriculaException {

        try{
            pstmt = con.prepareStatement(MATRICULAR_ALUMNO_QUERY);
            pstmt.setInt(1,alumno.getId());
            pstmt.setInt(2,asignatura.getIdentificador());
            pstmt.setBoolean(3,asignatura.isSuperada());
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new MatriculaException("Error al matricular al alumno: "+alumno.getNombre()+
                    " en la asignatura "+asignatura.getNombre());
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

package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Cafe;
import org.iesinfantaelena.utils.Utilidades;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class JDBCCafeDAO implements CafeDAO{

    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + "/src/main/resources/h2-properties.xml";

    // Consultas a realizar en BD
    private static final String SELECT_CAFES_QUERY = "";
    private static final String SEARCH_CAFE_QUERY = "";
    private static final String SEARCH_CAFE_PROV_QUERY = "";
    private static final String INSERT_CAFE_QUERY = "";
    private static final String DELETE_CAFE_QUERY = "";
    private static final String UPDATE_VENTAS_CAFE = "";
    private static final String UPDATE_INC_VENTAS_CAFE = "";
    private static final String UPDATE_TOTAL_CAFE = "";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstmt;

    /**
     * Constructor: inicializa conexión
     *
     * @throws AccesoDatosException
     */
    public JDBCCafeDAO() throws AccesoDatosException {
        try {
            // Obtenemos la conexión
            this.con = new Utilidades(PROPERTIES_FILE).getConnection();
            this.stmt = null;
            this.rs = null;
            this.pstmt = null;
        } catch (IOException e) {
            // Error al leer propiedades
            // En una aplicación real, escribo en el log y delego
            System.err.println(e.getMessage());
            throw new AccesoDatosException(
                    "Ocurrió un error al acceder a los datos");
        } catch (SQLException sqle) {
            // En una aplicación real, escribo en el log y delego
            // System.err.println(sqle.getMessage());
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException(
                    "Ocurrió un error al acceder a los datos");
        }

    }

    @Override
    public void verTabla() throws AccesoDatosException {

    }

    @Override
    public List<Cafe> buscar(String nombre) throws AccesoDatosException {
        return null;
    }

    @Override
    public void insertar(String nombre, int provid, float precio, int ventas, int total) throws AccesoDatosException {

    }

    @Override
    public void borrar(String nombre) throws AccesoDatosException {

    }

    @Override
    public List<Cafe> cafesPorProveedor(int provid) throws AccesoDatosException {
        return null;
    }

    @Override
    public void transferencia(String cafe1, String cafe2) throws AccesoDatosException {

    }

    @Override
    public Cafe obtener(Cafe cafe) throws AccesoDatosException {
        return null;
    }

    @Override
    public void insertar(Cafe cafe) throws AccesoDatosException {

    }

    @Override
    public void borrar(Cafe cafe) throws AccesoDatosException {

    }

    @Override
    public void actualizar(Cafe cafe) throws AccesoDatosException {

    }

    @Override
    public void cerrar() {

    }

    @Override
    public void liberar() {

    }
}

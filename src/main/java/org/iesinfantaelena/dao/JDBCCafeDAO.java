package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Cafe;
import org.iesinfantaelena.utils.Utilidades;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCafeDAO implements CafeDAO{

    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + "/src/main/resources/h2-properties.xml";

    // Consultas a realizar en BD
    private static final String SELECT_CAFES_QUERY = "select CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from CAFES";
    private static final String SEARCH_CAFE_QUERY = "select * from CAFES WHERE CAF_NOMBRE= ?";
    private static final String SEARCH_CAFE_PROV_QUERY = "select * from CAFES WHERE PROV_ID= ?";
    private static final String INSERT_CAFE_QUERY = "insert into CAFES values (?,?,?,?,?)";
    private static final String DELETE_CAFE_QUERY = "delete from CAFES WHERE CAF_NOMBRE = ?";
    private static final String UPDATE_CAFE_QUERY = "update CAFES set PROV_ID=?,PRECIO=?,VENTAS=?,TOTAL=? where CAF_NOMBRE = ?";
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
        try {
            // Creación de la sentencia
            stmt = con.createStatement();
            // Ejecución de la consulta y obtención de resultados en un ResultSet
            rs = stmt.executeQuery(SELECT_CAFES_QUERY);
            // Recuperación de los datos del ResultSet
            while (rs.next()) {
                String coffeeName = rs.getString("CAF_NOMBRE");
                int supplierID = rs.getInt("PROV_ID");
                float PRECIO = rs.getFloat("PRECIO");
                int VENTAS = rs.getInt("VENTAS");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + ", " + supplierID + ", "
                        + PRECIO + ", " + VENTAS + ", " + total);
            }

        } catch (SQLException sqle) {
            // En una aplicación real, escribo en el log y delego
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } finally {
            liberar();
        }
    }

    @Override
    public List<Cafe> buscar(String nombre) throws AccesoDatosException {
        ArrayList<Cafe> listaCafes = new ArrayList<>();
        try {
            // Creación de la sentencia
            pstmt = con.prepareStatement(SEARCH_CAFE_QUERY);
            pstmt.setString(1, nombre);
            // Ejecución de la consulta y obtención de resultados en un
            // ResultSet
            rs = pstmt.executeQuery();
            // Recuperación de los datos del ResultSet
            if (rs.next()) {
                String CAF_NOMBRE = rs.getString("CAF_NOMBRE");
                int PROV_ID = rs.getInt("PROV_ID");
                float PRECIO = rs.getFloat("PRECIO");
                int VENTAS = rs.getInt("VENTAS");
                int TOTAL = rs.getInt("TOTAL");
                //Se añade el cafe la lista
                listaCafes.add(new Cafe(CAF_NOMBRE,PROV_ID,PRECIO,VENTAS,TOTAL));
            }
        } catch (SQLException sqle) {
            // En una aplicación real, escribo en el log y delego
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } finally {
            liberar();
        }
        return listaCafes;
    }

    @Override
    public void insertar(String nombre, int provid, float precio, int ventas, int total) throws AccesoDatosException {
        try {
            pstmt = con.prepareStatement(INSERT_CAFE_QUERY);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, provid);
            pstmt.setFloat(3, precio);
            pstmt.setInt(4, ventas);
            pstmt.setInt(5, total);
            // Ejecución de la inserción
            pstmt.executeUpdate();
            System.out.println("Insercion del cafe: "+nombre+" realizada con exito");
        } catch (SQLException sqle) {
            // En una aplicación real, escribo en el log y delego
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } finally {
            liberar();
        }
    }

    @Override
    public void borrar(String nombre) throws AccesoDatosException {
        try {
            // Creación de la sentencia
            pstmt = con.prepareStatement(DELETE_CAFE_QUERY);
            pstmt.setString(1, nombre);
            // Ejecución del borrado
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected>0)
                System.out.println("café "+nombre+ " ha sido borrado.");

        } catch (SQLException sqle) {
            // En una aplicación real, escribo en el log y delego
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } finally {
            liberar();
        }
    }

    @Override
    public List<Cafe> cafesPorProveedor(int provid) throws AccesoDatosException {
        ArrayList<Cafe> listaCafes = new ArrayList<>();
        try {
            //Se establece conexion con la base de datos
            //con = new Utilidades().getConnection();
            // Creacion de la consulta con parametros
            pstmt = con.prepareStatement(SEARCH_CAFE_PROV_QUERY);
            pstmt.setInt(1, provid);
            //Obtencion de resultado de la query
            rs = pstmt.executeQuery();
            //Lectura de las diferentes columnas recuperadas
            while (rs.next()) {
                //info cafes
                String CAF_NOMBRE = rs.getString("CAF_NOMBRE");
                int PROV_ID = rs.getInt("PROV_ID");
                float PRECIO = rs.getFloat("PRECIO");
                int VENTAS = rs.getInt("VENTAS");
                int TOTAL = rs.getInt("TOTAL");
                //Se guardan en la lista los cafes suministrados por el proveedor
                listaCafes.add(new Cafe(CAF_NOMBRE,PROV_ID,PRECIO,VENTAS,TOTAL));
            }
        } catch (SQLException sqle) {
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } finally { //Libera todos los recursos
            liberar();
        }

        return listaCafes;
    }

    @Override
    public void transferencia(String cafe1, String cafe2) throws AccesoDatosException {

    }

    @Override
    public Cafe obtener(Cafe cafe) throws AccesoDatosException {
        //Si el cafe buscado esta en la BD solo habrá uno con ese nombre ya que el la PK
        //Por lo que no tiene sentido comprobar otros tamaños de lista
        List<Cafe> cafeBuscado = this.buscar(cafe.getNombre());
        if (cafeBuscado.size() == 1){
            return cafeBuscado.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void insertar(Cafe cafe) throws AccesoDatosException {
        this.insertar(cafe.getNombre(),cafe.getProvid(),cafe.getPrecio(),cafe.getVentas(), cafe.getTotal());
    }

    @Override
    public void borrar(Cafe cafe) throws AccesoDatosException {
        this.borrar(cafe.getNombre());
    }

    @Override
    public void actualizar(Cafe cafe) throws AccesoDatosException {
        try{
            pstmt = con.prepareStatement(UPDATE_CAFE_QUERY);
            pstmt.setInt(1,cafe.getProvid());
            pstmt.setFloat(2,cafe.getPrecio());
            pstmt.setInt(3,cafe.getVentas());
            pstmt.setInt(4,cafe.getTotal());
            pstmt.setString(5,cafe.getNombre());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Datos del cafe "+cafe.getNombre()+" ACTUALIZADOS");
            }
        }catch (SQLException sqle){
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Ocurrió un error al ACTUALIZAR los datos del cafe");
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

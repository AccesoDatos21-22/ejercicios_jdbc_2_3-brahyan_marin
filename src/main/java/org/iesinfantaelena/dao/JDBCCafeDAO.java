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

    private static final String CREATE_TABLE_CAFES ="create table if not exists CAFES (CAF_NOMBRE varchar(32) NOT NULL, PROV_ID int NOT NULL, PRECIO numeric(10,2) NOT NULL, VENTAS integer NOT NULL, TOTAL integer NOT NULL, PRIMARY KEY (CAF_NOMBRE), FOREIGN KEY (PROV_ID) REFERENCES PROVEEDORES(PROV_ID));";
    private static final String CREATE_TABLE_PROVEEDORES ="create table if not exists proveedores (PROV_ID integer NOT NULL, PROV_NOMBRE varchar(40) NOT NULL, CALLE varchar(40) NOT NULL, CIUDAD varchar(20) NOT NULL, PAIS varchar(2) NOT NULL, CP varchar(5), PRIMARY KEY (PROV_ID));";
    private Connection con;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    /**
     * Constructor: inicializa conexión
     *
     * @throws AccesoDatosException
     */
    public JDBCCafeDAO() throws AccesoDatosException {
        try {
            // Obtenemos la conexión
            this.con = new Utilidades(PROPERTIES_FILE).getConnection();

            this.stmt = con.createStatement();

            this.stmt.executeUpdate(CREATE_TABLE_PROVEEDORES);
            this.stmt.executeUpdate(CREATE_TABLE_CAFES);
            //Se crea proveedor para poder realizar las pruebas
            stmt.executeUpdate("insert into proveedores values(150, 'The High Ground', '100 Coffee Lane', 'Meadows', 'CA', '93966');");

        } catch (IOException e) {
            // Error al leer propiedades
            // En una aplicación real, escribo en el log y delego
            System.err.println(e.getMessage());
            throw new AccesoDatosException("Ocurrió un error al acceder al fichero de propiedades de la BD");
        } catch (SQLException sqle) {
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Error al establecer la conexion a la BD");
        }finally {
            liberar();
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

        try{
            int ventasCafe1 = 0;
            this.con.setAutoCommit(false);//Los cambios no son automaticos
            //Consulta y actualizacon cafe 1
            this.pstmt = con.prepareStatement(SEARCH_CAFE_QUERY, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            this.pstmt.setString(1,cafe1);
            this.rs = this.pstmt.executeQuery();
            while (rs.next()){
                //Guardamos valor de las ventas del primer cafe y las ponemos a cero
                ventasCafe1 = rs.getInt(4);
                rs.updateInt(4,0);
                rs.updateRow();
            }
            //Consulta y actualizacion cafe 2
            this.pstmt.setString(1,cafe2);
            this.rs = this.pstmt.executeQuery();

            while( rs.next() ){
                ventasCafe1 += rs.getInt(4);//Sumamos ambas ventas
                rs.updateInt(4,ventasCafe1);
                rs.updateRow();
            }
            con.commit();//Se aplicacn cambios a la BD
        }catch (SQLException sqle){
            //Se desacen los cambios el caso de error
            try{this.con.rollback();}
            catch (SQLException sqleRollback){
                throw new AccesoDatosException("ERROR AL DESHACER LA TRANSACCION");
            }
            Utilidades.printSQLException(sqle);
            throw new AccesoDatosException("Error al realizar la tranferencia entre cafes");
        }finally {
            liberar();
            try{
                this.con.setAutoCommit(true);//Se vuelven a hacer los cambios automaticos
            }catch (SQLException sqle){

            }
        }
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

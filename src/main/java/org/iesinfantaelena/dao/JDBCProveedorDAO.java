package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Proveedor;
import org.iesinfantaelena.utils.Utilidades;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCProveedorDAO implements  ProveedorDAO{

    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + "/src/main/resources/h2-properties.xml";

    private static final String CREATE_TABLE_PROVEEDORES ="create table if not exists PROVEEDORES (PROV_ID integer NOT NULL, PROV_NOMBRE varchar(40) NOT NULL, CALLE varchar(40) NOT NULL, CIUDAD varchar(20) NOT NULL, PAIS varchar(2) NOT NULL, CP varchar(5), PRIMARY KEY (PROV_ID));";
    private static final String SEARCH_PROVEEDOR_QUERY = "select * from PROVEEDORES where PROV_NOMBRE=?;";

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    //Nombre campos
    private static String ID_PROV = "PROV_ID";
    private static String NOMBRE_PROV = "PROV_NOMBRE";
    private static String CALLE = "CALLE";
    private static String CIUDAD = "CIUDAD";
    private static String PAIS = "PAIS";
    private static String CP = "CP";

    public JDBCProveedorDAO() throws AccesoDatosException {
        try {
            //Se establece conexion y se crea tabla si no existe
            con = new Utilidades(PROPERTIES_FILE).getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(CREATE_TABLE_PROVEEDORES);
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al establecer la conexion con la BD y crear la tabla");
        } catch (IOException e) {
            System.err.println("Error en la lectura del fichero de propiedades de la BD");
        }
    }
    //orden de las columnas = PROV_ID,PROV_NOMBRE,CALLE,CIUDAD,PAIS,CP
    @Override
    public List<Proveedor> buscar(String nombre) throws AccesoDatosException {

        ArrayList<Proveedor> listaProveedores = new ArrayList<>();
        try{
            //Preparada query
            pstmt = con.prepareStatement(SEARCH_PROVEEDOR_QUERY);
            pstmt.setString(1,nombre);
            //Ejecutada query
            rs=pstmt.executeQuery();
            //Obtencion de datos y creacion del objeto a devolver
            while(rs.next()){
                String name = rs.getString(NOMBRE_PROV);
                String street = rs.getString(CALLE);
                String country = rs.getString(PAIS);
                String zip = rs.getString(CP);
                String city = rs.getString(CIUDAD);
                int id = Integer.parseInt( rs.getString(ID_PROV) );
                listaProveedores.add(new Proveedor(id,name,street,city,country,zip));
            }
        }catch (SQLException sqle){
            throw new AccesoDatosException("Error en busqueda del proveedor: "+nombre);
        }finally {
            liberar();
        }

        return listaProveedores;
    }

    @Override
    public Proveedor buscar(Proveedor proveedor) throws AccesoDatosException {
        return null;
    }

    @Override
    public void insertar(Proveedor proveedor) throws AccesoDatosException {

    }

    @Override
    public void actualizar(Proveedor proveedor) throws AccesoDatosException {

    }

    @Override
    public void eliminar(Proveedor proveedor) throws AccesoDatosException {

    }

    @Override
    public void cerrar() {

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

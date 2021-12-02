package org.iesinfantaelena.dao;

public class  FactoriaDAO {

    private static FactoriaDAO instance;
    private static final String alumnoDAO = "JDBCAlumnoDAO";
    private static final String asignaturaDAO = "JDBCAsignaturaDAO";
    private static final String cafeDAO = "JDBCCafeDAO";
    private static final String proveedorDAO = "JDBCProveedorDAO";

    // Patrón Singleton
    // Garantiza que una clase sólo tenga una instancia y proporciona un punto
    // de acceso global a ella.
    // Si un objeto no tiene estado (atributos), no necesitamos múltiples
    // instancias
    // del mismo cargadas en memoria
    // No utilizamos métodos estáticos, pues perderiamos todas las ventajas de
    // la OO
    // como la herencia
    // Si tuviésemos atributos de objetos-->!!No usar singleton
    public static FactoriaDAO getInstance() {
        if (instance == null) {
            instance = new FactoriaDAO();
        }
        return instance;
    }

    private FactoriaDAO() {

    }

    /**
     * Devuelve un objeto DAO adecuado dependiendo de como esté implementada la
     * persistencia a datos
     *
     * @return
     * @throws AccesoDatosException
     */
    public AlumnoDAO getAlumnoDAO() throws AccesoDatosException {

        AlumnoDAO studentDAO = null;

        if(alumnoDAO.equals("JDBCAlumnoDAO")){
            studentDAO = new JDBCAlumnoDAO();
        }
        return studentDAO;

    }

    /**
     * Devuelve un objeto DAO adecuado dependiendo de como esté implementada la
     * persistencia a datos
     *
     * @return
     * @throws AccesoDatosException
     */
    public AsignaturaDAO getAsignaturaDAO() throws AccesoDatosException {

        AsignaturaDAO asigDAO = null;

        if( asignaturaDAO.equals("JDBCAsignaturaDAO") ){
            asigDAO = new JDBCAsignaturaDAO();
        }

        return asigDAO;

    }

    /**
     * Devuelve un objeto DAO adecuado dependiendo de como esté implementada la
     * persistencia a datos
     *
     * @return
     * @throws AccesoDatosException
     */
    public CafeDAO getCafeDAO() throws AccesoDatosException {
        /*
         * Como esta clase es estática no podemos hacerlo usando Class.forName, pues no se puede determinar el tipo en t.compilación
         * try { return (LibroDAO) Class.forName(libroDAO).newInstance(); }
         * catch (Exception e) {
         * System.err.println(e.getMessage()+"/n"+e.toString()); throw new
         * AccesoDatosException( "Ocurrió un error al acceder a los datos"); }
         */

		/*TODO: en un caso real el tipo del DAO se lee de un fichero de propiedades y/ó se utiliza Class.forName().newInstance() si la
		  clase no es estática. Al leerlo de un fichero de propiedades conseguimos que si el tipo del DAO cambia, no hay que tocar el código,y
		  por tanto no hay que regenerar el bytecode*/

        //Podemos inicializar el DAO a null o a uno por defecto
        CafeDAO cdao = null;

        //Un if por cada tipo de DAO posible a instanciar
        if (cafeDAO.equals("JDBCCafeDAO")) {
            cdao = new JDBCCafeDAO();
        }
        return cdao;

    }

    /**
     * Devuelve un objeto DAO adecuado dependiendo de como esté implementada la
     * persistencia a datos
     *
     * @return
     * @throws AccesoDatosException
     */
    public ProveedorDAO getProveedorDAO() throws AccesoDatosException {

        ProveedorDAO pdao = null;

        if(proveedorDAO.equals("JDBCProveedorDAO")){
            pdao = new JDBCProveedorDAO();
        }
       return pdao;
    }


}



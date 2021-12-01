import org.iesinfantaelena.dao.AccesoDatosException;
import org.iesinfantaelena.dao.CafeDAO;
import org.iesinfantaelena.dao.FactoriaDAO;
import org.iesinfantaelena.model.Cafe;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCafeDAO {

    private static CafeDAO cafeDAO;

    @BeforeEach
    void limpiar() throws AccesoDatosException {//Limpia la BD para que no haya claves repetidas
        //Ya de paso sirve para probar el borrar
        cafeDAO.borrar("Cafetito");
        cafeDAO.borrar("Cafe tacilla");
    }


    @BeforeAll
    @DisplayName("La conexión no debería lanza una excepción")
    @Test
    static void conexion() {
        assertDoesNotThrow(() -> {
             cafeDAO= FactoriaDAO.getInstance().getCafeDAO();
        });
    }

    @Test
    void insertar() {
        assertDoesNotThrow(() -> {
            Cafe cafe = new Cafe("Cafetito", 150, 1.0f, 100,1000);
            cafeDAO.insertar(cafe);
            assertEquals(cafe, cafeDAO.obtener(cafe));
        });
    }

    // añadir resto de tests
    @Test
    void buscar(){
        assertDoesNotThrow( () -> {
            Cafe cafeInsertado = new Cafe("Cafetito", 150, 1.0f, 100,1000);
            cafeDAO.insertar(cafeInsertado);
            Cafe cafeBuscado = cafeDAO.buscar("Cafetito").get(0);
            assertEquals(cafeBuscado,cafeInsertado);
        });
    }

    @Test
    void cafesPorProveedor(){
        assertDoesNotThrow( () ->{
            //Se insertan cafes
            Cafe cafeInsertado1 = new Cafe("Cafetito", 150, 1.0f, 100,1000);
            Cafe cafeInsertado2 = new Cafe("Cafe tacilla", 150, 2.0f, 100,1000);
            cafeDAO.insertar(cafeInsertado1);
            cafeDAO.insertar(cafeInsertado2);

            List<Cafe> cafesDevueltos = cafeDAO.cafesPorProveedor(150);
            assertTrue(cafesDevueltos.contains(cafeInsertado1));
            assertTrue(cafesDevueltos.contains(cafeInsertado2));
        });
    }

    @Test
    void actualizar(){
        assertDoesNotThrow( () -> {
            //Insercion del Cafe
            Cafe cafeInsertado = new Cafe("Cafetito", 150, 1.0f, 100,1000);
            cafeDAO.insertar(cafeInsertado);
            //Modificacion del cafe
            cafeInsertado.setTotal(0);
            cafeInsertado.setPrecio(500f);
            //Actualizacion de la BD
            cafeDAO.actualizar(cafeInsertado);
            //Comprueba que la BD ha volcado los datos correctamente
            assertEquals(cafeDAO.buscar(cafeInsertado.getNombre()).get(0),cafeInsertado);
        });
    }

    @Test
    void transferencia(){
        assertDoesNotThrow( () -> {
            //Insercion de cafes
            Cafe cafeInsertado1 = new Cafe("Cafetito", 150, 1.0f, 100,1000);
            Cafe cafeInsertado2 = new Cafe("Cafe tacilla", 150, 2.0f, 100,1000);
            cafeDAO.insertar(cafeInsertado1);
            cafeDAO.insertar(cafeInsertado2);
            //Se realiza tranferencia
            cafeDAO.transferencia(cafeInsertado1.getNombre(),cafeInsertado2.getNombre());
            //Se obtiene la informacion actualizada de cada cafe
            cafeInsertado1 = cafeDAO.obtener(cafeInsertado1);
            cafeInsertado2 = cafeDAO.obtener(cafeInsertado2);
            //Se comprueba que han cambiado las ventas de cada cafe
            assertEquals(0,cafeInsertado1.getVentas());
            assertEquals(200,cafeInsertado2.getVentas());

        });
    }

    @AfterAll
    static void cerrarConexion(){//Cierra la conexion despues de ejecutar todos los test
        cafeDAO.cerrar();
    }
}

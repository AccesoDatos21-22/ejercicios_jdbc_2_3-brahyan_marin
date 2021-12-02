import org.iesinfantaelena.dao.AccesoDatosException;
import org.iesinfantaelena.dao.CafeDAO;
import org.iesinfantaelena.dao.FactoriaDAO;
import org.iesinfantaelena.model.Cafe;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCafeDAO {

    private static CafeDAO cafeDAO;

    Cafe cafe1 = new Cafe("Cafetito", 150, 1.0f, 100,1000);
    Cafe cafe2 = new Cafe("Cafe tacilla", 150, 2.0f, 100,1000);

    Cafe cafeControl = new Cafe("Cafe Invent",150,25f,500,12500);

    @DisplayName("La conexión no debería lanza una excepción")
    @Test
    @Order(1)
    void conexion() {
        assertDoesNotThrow(() -> {
             cafeDAO= FactoriaDAO.getInstance().getCafeDAO();
        });
    }

    @Test
    @Order(2)
    void insertar() {
        assertDoesNotThrow(() -> {
            //Insertados cafes
            cafeDAO.insertar(cafe1);
            cafeDAO.insertar(cafe2);
            //Comprobamos que los cafes estan en la BD
            assertEquals(cafe1, cafeDAO.obtener(cafe1));
            assertEquals(cafe2, cafeDAO.obtener(cafe2));
            //Comprobamos que el cafe de control no esta
            assertNotEquals(cafeControl, cafeDAO.obtener(cafeControl));
        });
    }

    // añadir resto de tests
    @Test
    @Order(3)
    void buscar(){
        assertDoesNotThrow( () -> {
            //Evaluamos que los cafes encontrados coinciden con los insertados
            assertTrue(cafeDAO.buscar("Cafetito").contains(cafe1));
            assertTrue(cafeDAO.buscar("Cafe tacilla").contains(cafe2));
            //El cafe no insertado no esta en la BD
            assertFalse(cafeDAO.buscar("Cafe Invent").contains(cafeControl));
        });
    }

    @Test
    @Order(4)
    void obtener(){
        assertDoesNotThrow(() ->{
            //Los cafes insertados estan en la BD
            assertEquals(cafe1, cafeDAO.obtener(cafe1));
            assertEquals(cafe2, cafeDAO.obtener(cafe2));
            //El cafe no insertado no esta en BD
            assertNotEquals(cafeControl,cafeDAO.obtener(cafeControl));
        });
    }

    @Test
    @Order(5)
    void cafesPorProveedor(){
        assertDoesNotThrow( () ->{
            //Obtenemos todos los cafes del proveedor
            List<Cafe> cafesDevueltos = cafeDAO.cafesPorProveedor(150);
            //Comprobamos que la lista contiene los cafes
            assertTrue(cafesDevueltos.contains(cafe1));
            assertTrue(cafesDevueltos.contains(cafe2));
            //Comprobamos que no esta el no introducido
            assertFalse(cafesDevueltos.contains(cafeControl));
        });
    }

    @Test
    @Order(6)
    void actualizar(){
        assertDoesNotThrow( () -> {
            //Modificacion del cafe1
            cafe1.setTotal(0);
            cafe1.setPrecio(500f);
            //Se comprueba que antes de actualizar NO son el mismo objeto
            assertNotEquals(cafe1, cafeDAO.obtener(cafe1));
            //Actualizacion del cafe1 en la BD
            cafeDAO.actualizar(cafe1);
            //Comprueba que la BD ha volcado los datos correctamente
            assertEquals(cafeDAO.obtener(cafe1),cafe1);
        });
    }

    @Test
    @Order(7)
    void transferencia(){
        assertDoesNotThrow( () -> {
            //Ventas que debe tener el sundo cafe al final
            int ventasFinal = cafe1.getVentas() + cafe2.getVentas();
            //Se realiza tranferencia
            cafeDAO.transferencia(cafe1.getNombre(),cafe2.getNombre());
            //Se actualizan los obejetos del test a la informacion modificada de la BD
            cafe1 = cafeDAO.obtener(cafe1);
            cafe2 = cafeDAO.obtener(cafe2);
            //Se comprueba que han cambiado las ventas de cada cafe
            assertEquals(0,cafe1.getVentas());
            assertEquals(ventasFinal,cafe2.getVentas());
        });
    }

    @Test
    @Order(8)
    void borrar() {
        assertDoesNotThrow(()->{
            //Borra los 2 cafes de la BD
            cafeDAO.borrar(cafe1);
            cafeDAO.borrar(cafe2);
        });
    }

    @AfterAll
    static void cerrarConexion(){//Cierra la conexion despues de ejecutar todos los test
        cafeDAO.cerrar();
    }
}

import org.iesinfantaelena.dao.AsignaturaDAO;
import org.iesinfantaelena.dao.FactoriaDAO;
import org.iesinfantaelena.dao.MatriculaException;
import org.iesinfantaelena.model.Alumno;
import org.iesinfantaelena.model.Asignatura;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAsignaturaDAO {

    private static AsignaturaDAO asignaturaDAO;

    private static Asignatura asignatura1 = new Asignatura(58964,"OB","PROGRAMACION AVANZADA",6f);
    private static Asignatura asignatura2 = new Asignatura(74586,"TR","FUNDAMENTOS FISICOS",4.5f);
    private static Asignatura asignatura3 = new Asignatura(12345,"OP","MACHINE LEARNING",6f);
    @Test
    @Order(1)
    void conectar(){
        assertDoesNotThrow(()->{
            asignaturaDAO = FactoriaDAO.getInstance().getAsignaturaDAO();
        });
    }

    @Test
    @Order(2)
    void insertar(){
        assertDoesNotThrow(()->{
            //Se insertan asignaturas
            asignaturaDAO.insertar(asignatura1);
            asignaturaDAO.insertar(asignatura2);
            asignaturaDAO.insertar(asignatura3);
            //Se comprueba que estan en la BD
            assertEquals(asignatura1,asignaturaDAO.buscar(asignatura1.getIdentificador()));
            assertEquals(asignatura2,asignaturaDAO.buscar(asignatura2.getIdentificador()));
            assertEquals(asignatura3,asignaturaDAO.buscar(asignatura3.getIdentificador()));
        });
        assertThrows(MatriculaException.class,()->{
            //Deberia lanzar MatriculaException ya que existe la asignatura en la BD
            asignaturaDAO.insertar(asignatura1);
        });
    }

    @Test
    @Order(3)
    void buscarPorId (){
        assertDoesNotThrow(()->{
            //Asignatura insertada desde el el script
            Asignatura asignaturaScript = new Asignatura(32330,"OP","APLICACIONES DISTRIBUIDAS PARA BIOINGENIERIA",3f);
            assertEquals(asignaturaScript,asignaturaDAO.buscar(asignaturaScript.getIdentificador()));
            //Asignaturas insertadas desde el test
            assertEquals(asignatura1,asignaturaDAO.buscar(asignatura1.getIdentificador()));
            assertEquals(asignatura2,asignaturaDAO.buscar(asignatura2.getIdentificador()));
            assertEquals(asignatura3,asignaturaDAO.buscar(asignatura3.getIdentificador()));
            //Comprobacion de que no esta en la BD una asignatura no introducida
            assertNull(asignaturaDAO.buscar(2));
        });
    }

    @Test
    @Order(4)
    void buscarPorNombre(){
        assertDoesNotThrow(()->{
            //Alumnos buscados por nombre
            assertTrue(asignaturaDAO.buscar(asignatura1.getNombre()).contains(asignatura1));
            assertTrue(asignaturaDAO.buscar(asignatura2.getNombre()).contains(asignatura2));
            assertTrue(asignaturaDAO.buscar(asignatura3.getNombre()).contains(asignatura3));
        });
    }

    @Test
    @Order(5)
    void borrar(){
        assertDoesNotThrow(()->{
            //Borrado asignaturas
            asignaturaDAO.borrar(asignatura1);
            asignaturaDAO.borrar(asignatura2);
            asignaturaDAO.borrar(asignatura3);
            //Se comprueba que se han borrado correctamente
            assertNull(asignaturaDAO.buscar(asignatura1.getIdentificador()));
            assertNull(asignaturaDAO.buscar(asignatura2.getIdentificador()));
            assertNull(asignaturaDAO.buscar(asignatura3.getIdentificador()));
        });
    }

    @AfterAll
    static void cerrarConexion(){
        asignaturaDAO.cerrar();
    }
}


import org.iesinfantaelena.dao.AlumnoDAO;
import org.iesinfantaelena.dao.FactoriaDAO;
import org.iesinfantaelena.dao.MatriculaException;
import org.iesinfantaelena.model.Alumno;
import org.iesinfantaelena.model.Asignatura;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAlumnoDAO {

    private static AlumnoDAO alumnoDao;

    private static Alumno alumno1 = new Alumno(5000001,"Garcia Menendez","Juanito",2,1);
    private static Alumno alumno2 = new Alumno(5000002,"Bellido Gonzalez","Carlos",1,1);
    private static Alumno alumno3 = new Alumno(5000003,"Macareno Fernandez","Jose",3,1);

    private static Asignatura asignatura1 = new Asignatura();

    @Test
    @Order(1)
    void conectar (){
        assertDoesNotThrow(()->{
            //Este metodo ejecuta el scrip de creacion de BD
            alumnoDao = FactoriaDAO.getInstance().getAlumnoDAO();
        });
    }

    @Test
    @Order(2)
    void insertar(){
        assertDoesNotThrow( ()->{
            //Insercion de alumnos
            alumnoDao.insertar(alumno1);
            alumnoDao.insertar(alumno2);
            alumnoDao.insertar(alumno3);
            //Se compruba que se han añadido los alumnos
            assertEquals(alumno1,alumnoDao.buscar(alumno1.getId()));
            assertEquals(alumno2,alumnoDao.buscar(alumno2.getId()));
            assertEquals(alumno3,alumnoDao.buscar(alumno3.getId()));
        });
        assertThrows(MatriculaException.class,()->{
            //Deberia lanzar MatriculaException ya que existe el alumno en la BD
            alumnoDao.insertar(alumno1);
        });
    }

    @Test
    @Order(3)
    void buscarPorId (){
        assertDoesNotThrow(()->{
            //Alumno insertado desde el el script
            Alumno alumnoScript = new Alumno(9119705,"JIMENEZ ALONSO","DIEGO",4,3);
            assertEquals(alumnoScript,alumnoDao.buscar(alumnoScript.getId()));
            //Alumnos insertados desde el test
            assertEquals(alumno1,alumnoDao.buscar(alumno1.getId()));
            assertEquals(alumno2,alumnoDao.buscar(alumno2.getId()));
            assertEquals(alumno3,alumnoDao.buscar(alumno3.getId()));
        });
    }

    @Test
    @Order(4)
    void buscarPorNombre(){
        assertDoesNotThrow(()->{
            //Alumnos buscados por nombre
            assertTrue(alumnoDao.buscar(alumno1.getNombre()).contains(alumno1));
            assertTrue(alumnoDao.buscar(alumno2.getNombre()).contains(alumno2));
            assertTrue(alumnoDao.buscar(alumno3.getNombre()).contains(alumno3));
        });
    }

    @Test
    @Order(5)
    void matricular(){
        assertDoesNotThrow(()->{
            //Matriculacion de alumnos
            alumnoDao.matricular(alumno1,asignatura1);
            alumnoDao.matricular(alumno2,asignatura1);
            alumnoDao.matricular(alumno3,asignatura1);
        });
    }

    @Test
    @Order(6)
    void borrar(){
        assertDoesNotThrow(()->{
            //Borrado alumnos
            alumnoDao.borrar(alumno1.getId());
            alumnoDao.borrar(alumno2.getId());
            alumnoDao.borrar(alumno3.getId());
            //Se comprueba que se han borrado correctamente
            assertNull(alumnoDao.buscar(alumno1.getId()));
            assertNull(alumnoDao.buscar(alumno2.getId()));
            assertNull(alumnoDao.buscar(alumno3.getId()));
        });
    }
}


import org.iesinfantaelena.dao.AlumnoDAO;
import org.iesinfantaelena.dao.FactoriaDAO;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAlumnoDAO {

    private static AlumnoDAO alumnoDao;

    @Test
    @Order(1)
    void conectar (){
        assertDoesNotThrow(()->{
            alumnoDao = FactoriaDAO.getInstance().getAlumnoDAO();
        });
    }

    @Test
    @Order(2)
    void buscar (){
        assertDoesNotThrow(()->{

        });
    }
}

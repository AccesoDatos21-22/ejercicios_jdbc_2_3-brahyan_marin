import org.iesinfantaelena.dao.*;
import org.iesinfantaelena.model.Cafe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCafeDAO {

    private CafeDAO cafeDAO;

    @Test
    @DisplayName("La conexión no debería lanza una excepción")
        void conexion() {
            assertDoesNotThrow(() -> {
                 cafeDAO= FactoriaDAO.getInstance().getCafeDAO();

            });
        }

    @Test
    void insertar() {
        assertDoesNotThrow(() -> {
            Cafe test;
            Cafe cafe = new Cafe("Cafetito", 150, 1.0f, 100,1000);
            cafeDAO.insertar(cafe);
            assertEquals(cafe, cafeDAO.obtener(cafe));
        });
    }

    // añadir resto de tests

}

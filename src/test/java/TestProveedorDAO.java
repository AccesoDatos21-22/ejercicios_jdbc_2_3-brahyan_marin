import org.iesinfantaelena.dao.FactoriaDAO;
import org.iesinfantaelena.dao.ProveedorDAO;
import org.iesinfantaelena.model.Proveedor;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestProveedorDAO {

    private static ProveedorDAO proveedorDAO = null;
    private static Proveedor proveedor1 = new Proveedor(49,"PROVerior Coffee","1 Party Place","Mendocino","CA",95460);
    private static Proveedor proveedor2 = new Proveedor(101,"Acme, Inc.","99 mercado CALLE","Groundsville","CA",95199);
    private static Proveedor proveedor3 = new Proveedor(150,"The High Ground","100 Coffee Lane","Meadows","CA",93966);
    private static Proveedor proveedor4 = new Proveedor(250,"Acme, Inc.","C/Castellana 25","Madrid","ES",28520);

    @Test
    @Order(1)
     void conectar(){//Obtencion de la implementacion de ProveedorDAO
        assertDoesNotThrow( () -> {
            proveedorDAO = FactoriaDAO.getInstance().getProveedorDAO();
        });
    }

    @Test
    @Order(2)
    void insertar(){//Se insertan los proveedores
        assertDoesNotThrow( ()->{
            proveedorDAO.insertar(proveedor1);
            proveedorDAO.insertar(proveedor2);
            proveedorDAO.insertar(proveedor3);
            proveedorDAO.insertar(proveedor4);
        });
    }

    @Test
    @Order(3)
    void buscarPorNombre(){
        assertDoesNotThrow( () ->{
            //Se comprueba que se obtienen los 2 proveedores con el mismo nombre pero distinto ID
            List<Proveedor> listaProveedores = proveedorDAO.buscar("Acme, Inc.");
            assertEquals(2,listaProveedores.size());
            //Se comprueba que el nombre de los proveedores recuperados coincida con el buscado
            assertEquals(listaProveedores.get(0).getNombre(),"Acme, Inc.");
            assertEquals(listaProveedores.get(1).getNombre(),"Acme, Inc.");
        });
    }

    @Test
    @Order(4)
    void buscarPorProveedor(){
        assertDoesNotThrow( () ->{
            //El proveedor devuelto es el mismo que el pasado por parametro
            Proveedor proveedorBuscado = proveedorDAO.buscar(proveedor3);
            assertEquals(proveedorBuscado,proveedor3);
        });
    }
    @Test
    @Order(5)
    void actualizar(){
        assertDoesNotThrow(()->{
            //modificacion de un proveedor
            proveedor2.setCalle("Calle invent 42");
            proveedor2.setCiudad("San Francisco");
            //Se comprueba que sea distinto al que esta en la BD
            assertNotEquals(proveedor2, proveedorDAO.buscar(proveedor2));
            //Se actualiza la informacion del proveedor
            proveedorDAO.actualizar(proveedor2);
            //se comprueba que sea igual al que devuelve la BD
            assertEquals(proveedor2,proveedorDAO.buscar(proveedor2));
        });
    }

    @Test
    @Order(6)
    void eliminar(){
        assertDoesNotThrow( () -> {
            //Se eliminan todos los proveedores
            proveedorDAO.eliminar(proveedor1);
            proveedorDAO.eliminar(proveedor2);
            proveedorDAO.eliminar(proveedor3);
            proveedorDAO.eliminar(proveedor4);
        });
    }

    @AfterAll
    static void cerrar(){
        proveedorDAO.cerrar();
    }
}

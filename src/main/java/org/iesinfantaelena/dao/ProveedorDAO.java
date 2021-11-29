package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Proveedor;

import java.util.List;

public interface ProveedorDAO {

    /**
     * Método que busca un proveedor por nombre
     *
     *
     * @param nombre nombre del proveedor
     * @return lista de proveedores
     * @throws AccesoDatosException excepción de Acceso a datos
     */
    public List<Proveedor> buscar(String nombre) throws AccesoDatosException;

    /**
     * Método que busca un proveedor por identificador y devuelve sus datos
     *
     * @param proveedor proveedor
     */
    public Proveedor buscar(Proveedor proveedor) throws AccesoDatosException;

    /**
     * Método para insertar una fila
     *
     * @param proveedor proveedor
     */
    public void insertar(Proveedor proveedor) throws AccesoDatosException;

    /**
     * Método para actualizar una fila
     *
     * @param proveedor proveedor
     */
    public void actualizar(Proveedor proveedor) throws AccesoDatosException;

    /**
     * Método para eliminar una fila
     *
     * @param proveedor proveedor
     */
    public void eliminar(Proveedor proveedor) throws AccesoDatosException;

    /**
     * Método para cerrar el DAO
     *
     * @throws AccesoDatosException excepción de Acceso a datos
     */

    public void cerrar();

    public void liberar();

}


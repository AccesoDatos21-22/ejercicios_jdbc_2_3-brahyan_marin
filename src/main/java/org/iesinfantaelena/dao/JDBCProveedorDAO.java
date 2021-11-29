package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Proveedor;

import java.util.List;

public class JDBCProveedorDAO implements  ProveedorDAO{
    @Override
    public List<Proveedor> buscar(String nombre) throws AccesoDatosException {
        return null;
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

    }
}

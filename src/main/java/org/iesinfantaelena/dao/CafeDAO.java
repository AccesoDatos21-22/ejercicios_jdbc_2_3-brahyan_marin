package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Cafe;

import java.util.List;

public interface CafeDAO {

    public void verTabla() throws AccesoDatosException;

    public List<Cafe> buscar(String nombre) throws AccesoDatosException;

    public void insertar(String nombre, int provid, float precio, int ventas,
                         int total) throws AccesoDatosException;

    public void borrar(String nombre)  throws AccesoDatosException;


    public List<Cafe> cafesPorProveedor(int provid) throws AccesoDatosException;

    public void transferencia(String cafe1, String cafe2) throws AccesoDatosException;

    // CRUD

    public Cafe obtener(Cafe cafe) throws AccesoDatosException;

    public void insertar(Cafe cafe) throws AccesoDatosException;

    public void borrar(Cafe cafe)  throws AccesoDatosException;

    public void actualizar(Cafe cafe)  throws AccesoDatosException;

    public void cerrar();

    public void liberar();

}
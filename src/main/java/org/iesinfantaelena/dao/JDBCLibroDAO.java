package org.iesinfantaelena.dao;

import org.iesinfantaelena.model.Libro;

import java.util.HashMap;
import java.util.List;

public class JDBCLibroDAO implements LibroDAO{
    @Override
    public List<Libro> verCatalogo() throws AccesoDatosException {
        return null;
    }

    @Override
    public void actualizarCopias(HashMap<Integer, Integer> copias) {

    }

    @Override
    public void actualizarCopias(Libro libro) throws AccesoDatosException {

    }

    @Override
    public void anadirLibro(Libro libro) throws AccesoDatosException {

    }

    @Override
    public void borrar(Libro libro) throws AccesoDatosException {

    }

    @Override
    public Libro obtenerLibro(int ISBN) throws AccesoDatosException {
        return null;
    }

    @Override
    public List<Libro> buscar(String nombre) throws AccesoDatosException {
        return null;
    }

    @Override
    public String[] getCamposLibro() throws AccesoDatosException {
        return new String[0];
    }

    @Override
    public void verCatalogoInverso() throws AccesoDatosException {

    }

    @Override
    public void verCatalogo(int[] filas) throws AccesoDatosException {

    }

    @Override
    public void rellenaPrecio(float precio) throws AccesoDatosException {

    }

    @Override
    public void actualizaPrecio(int isbn1, int isbn2, float precio) throws AccesoDatosException {

    }

    @Override
    public void actualizaPrecio(int isbn, float precio, int paginas) throws AccesoDatosException {

    }

    @Override
    public void cerrar() {

    }

    @Override
    public void liberar() {

    }
}

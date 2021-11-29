package org.iesinfantaelena.dao;


import java.util.List;
import java.util.HashMap;

import org.iesinfantaelena.model.Libro;

/**
 *
 * @author Carlos
 * @date 23/10/2021
 * @version 1.0
 * @license GPLv3
 */
public interface LibroDAO {

    public List<Libro> verCatalogo() throws AccesoDatosException;

    public void actualizarCopias(HashMap<Integer, Integer> copias);

    public void actualizarCopias(Libro libro) throws AccesoDatosException;

    public void anadirLibro(Libro libro) throws AccesoDatosException;

    public void borrar(Libro libro) throws AccesoDatosException;

    public Libro obtenerLibro(int ISBN) throws AccesoDatosException;

    public List<Libro> buscar(String nombre) throws AccesoDatosException;

    public String[] getCamposLibro() throws AccesoDatosException;

    public void verCatalogoInverso() throws AccesoDatosException;

    public void verCatalogo(int[] filas) throws AccesoDatosException;

    public void rellenaPrecio(float precio) throws AccesoDatosException;

    public void actualizaPrecio(int isbn1, int isbn2, float precio)	throws AccesoDatosException;

    public void actualizaPrecio(int isbn, float precio,  int paginas) throws AccesoDatosException;

    public void cerrar();

    public void liberar();
}


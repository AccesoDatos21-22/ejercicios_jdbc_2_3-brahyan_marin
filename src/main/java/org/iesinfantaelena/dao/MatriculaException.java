package org.iesinfantaelena.dao;

/**
 * Excepción lanzada cuando ocurre algun error en el acceso a la capa
 * persistente de datos(ficheros, base de datos...)
 */

public class MatriculaException extends Exception {

    public MatriculaException(String message) {
        super(message);
    }
}

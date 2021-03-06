package org.iesinfantaelena.model;

import java.util.Objects;

/**
 *  @descrition
 *	@author Carlos
 *  @date 27/10/2021
 *  @version 1.0
 *  @license GPLv3
 */

public class Proveedor {
    private int identificador = 0;
    private String nombre = "";
    private String calle = "";
    private String ciudad = "";
    private String pais = "";
    private int cp = 0;

    public Proveedor(){

    }

    public Proveedor(int id,String name,String street,String city,String country, int zip){
        identificador = id;
        nombre = name;
        calle = street;
        ciudad = city;
        pais = country;
        cp = zip;
    }

    //***************Equals y hash code**************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return identificador == proveedor.identificador && cp == proveedor.cp && Objects.equals(nombre, proveedor.nombre) && Objects.equals(calle, proveedor.calle) && Objects.equals(ciudad, proveedor.ciudad) && Objects.equals(pais, proveedor.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, nombre, calle, ciudad, pais, cp);
    }
    //****************Getters Setters************
    public int getIdentificador() {
        return identificador;
    }
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public int getCp() {
        return cp;
    }
    public void setCp(int cp) {
        this.cp = cp;
    }
    @Override
    public String toString() {
        return "Proveedor [nombre="
                + nombre + ", calle=" + calle + ", ciudad=" + ciudad
                + ", pais=" + pais + ", cp=" + cp + "]";
    }



}



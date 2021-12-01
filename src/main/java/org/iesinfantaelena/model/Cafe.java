package org.iesinfantaelena.model;

import java.util.Objects;

/**
 *  @descrition
 *	@author Carlos
 *  @date 27/10/2021
 *  @version 1.0
 *  @license GPLv3
 */

public class Cafe {

    private String nombre;
    private int provid;
    private float precio;
    private int ventas;
    private int total;

    public Cafe() {

    }

    public Cafe(String nombre, int provid, float precio, int ventas, int total) {
        this.nombre = nombre;
        this.provid = provid;
        this.precio = precio;
        this.ventas = ventas;
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cafe cafe = (Cafe) o;
        return provid == cafe.provid && Float.compare(cafe.precio, precio) == 0 && ventas == cafe.ventas && total == cafe.total && Objects.equals(nombre, cafe.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, provid, precio, ventas, total);
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getProvid() {
        return provid;
    }
    public void setProvid(int provid) {
        this.provid = provid;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public int getVentas() {
        return ventas;
    }
    public void setVentas(int ventas) {
        this.ventas = ventas;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}

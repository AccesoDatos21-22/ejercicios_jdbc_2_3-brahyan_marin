package org.iesinfantaelena;

import org.iesinfantaelena.model.Asignatura;

public class Main {
    public static void main (String[] args){
        System.out.println("");
        Asignatura asig1 = new Asignatura(1,"AD","OB",6f);
        Asignatura asig2 = new Asignatura(1,"AD","OB",6f);

        System.out.println(asig1.hashCode());
        System.out.println(asig2.hashCode());
        System.out.println(asig2.hashCode() == asig1.hashCode());
    }

}

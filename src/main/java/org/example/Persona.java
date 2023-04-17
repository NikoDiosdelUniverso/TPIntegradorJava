package org.example;

import java.util.ArrayList;

public class Persona {

    private int id; // atributo
    private String nombre; // atributo
    private ArrayList<Pronostico> listaDePronosticos; //atributo

    public Persona(int id, String nombre, ArrayList<Pronostico> pronosticos) { // constructor
        this.id = id;
        this.nombre = nombre;
        this.listaDePronosticos = pronosticos;
    }

    public int puntosPersona (){
        int puntos=0;

        for (Pronostico pronostico : listaDePronosticos) {
            puntos += pronostico.Puntos();
        }
        return puntos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPronostico (Pronostico pronostico){
        this.listaDePronosticos.add(pronostico);
    }

}

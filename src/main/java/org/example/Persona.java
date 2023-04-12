package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Persona {

    private int id; // atributo
    private String nombre; // atributo
    private ArrayList<Pronostico> listadepronosticos; //atributo
    private int puntaje; // atributo

    public Persona(int id, String nombre, ArrayList<Pronostico> pronosticos) { // constructor
        this.id = id;
        this.nombre = nombre;
        this.listadepronosticos = pronosticos;
    }

    public int puntoPersona (){

        int puntos=0;

        for (Pronostico pronostico : listadepronosticos) {
            puntos+=pronostico.Puntos();
        }
        return puntos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Pronostico> getPronosticos() {
        return listadepronosticos;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void agregarPronostico (Pronostico pronostico){

        this.listadepronosticos.add(pronostico);
    }
}

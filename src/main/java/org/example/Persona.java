package org.example;

import java.util.ArrayList;

public class Persona {

    private int id; // atributo
    private String nombre; // atributo
    private ArrayList<Pronostico> listaDePronosticos; //atributo

    private int puntaje;

    public Persona(int id, String nombre, ArrayList<Pronostico> pronosticos) { // constructor
        this.id = id;
        this.nombre = nombre;
        this.listaDePronosticos = pronosticos;
    }

    public int puntosPersona (int puntosPorPartido){
        int puntos=0;

        for (Pronostico pronostico : listaDePronosticos) {
            puntos += pronostico.Puntos() * puntosPorPartido;
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

    //método buscar pronostico por partido
    public Pronostico buscarPronostico(Partido partido) {
        for (Pronostico p: listaDePronosticos){
            if(p.getPartido().getId() == partido.getId()) {
                return p;
            }
        }
        return null;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void addPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }
}

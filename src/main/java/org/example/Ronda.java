package org.example;
import java.util.ArrayList;

public class Ronda {

    private int id; // atributo
    private ArrayList<Partido> listadepartidos; //atributo

    public Ronda(int id, ArrayList<Partido> listadepartidos) { // constructor
        this.id = id;
        this.listadepartidos = listadepartidos;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Partido> getListadepartidos() {
        return listadepartidos;
    }

    public void agregarPartido (Partido partido){

        this.listadepartidos.add(partido);
    }
}

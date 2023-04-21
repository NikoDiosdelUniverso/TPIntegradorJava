package org.example;

import java.util.ArrayList;

public class Fase {
    private int id; // atributo
    private ArrayList<Ronda> ListaDeRondas; // atributo

    public Fase(int id) { // constructor
        this.id = id;
        this.ListaDeRondas = new ArrayList<>();
    }
    public int getId() { // getter (permite leer el id ya que es privado)
        return id;
    }

    public ArrayList<Ronda> getListaDeRondas() {
        return ListaDeRondas;
    }

    public void agregarRondaALaFase(Ronda ronda) { // método para agregar rondas
        if(buscarRondaPorId(ronda.getId()) == null){
            ListaDeRondas.add(ronda);
        }
    }

    public Ronda buscarRondaPorId (int id){
        for (Ronda r : this.ListaDeRondas){
            if (r.getId() == id){
                return r;
            }
        }
        return null;
    }
}


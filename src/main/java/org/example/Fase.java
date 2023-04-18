package org.example;

import java.util.ArrayList;

public class Fase {
    private int id; // atributo
    private ArrayList<Ronda> ListaDeRondas; // atributo

    public Fase(int id) { // constructor
        this.id = id;
        this.ListaDeRondas = new ArrayList<>();
    }
    public int getId() { // getter (permite leer el id ya q es privado)
        return id;
    }

    public void agregarRondaALaFase(Ronda ronda) { // método para agregar rondas
        for (Ronda r : this.ListaDeRondas){
            if(r.getId() == ronda.getId()){
                throw new RuntimeException("Ya existe la ronda");
            }
        }
        ListaDeRondas.add(ronda);
    }

    public Ronda buscarRondaPorId (int id){
        for (Ronda r : this.ListaDeRondas){
            if (r.getId() == id){
                return r;
            }
        }
        return null;
    }

    public void CrearFase (ArrayList<Fase> ListaDeFases, Fase fase){
        for (Fase f : ListaDeFases){
            if(f.getId() == fase.getId()){
                throw new RuntimeException("Ya existe la fase");
            }
        }
        ListaDeFases.add(fase);
    }
}


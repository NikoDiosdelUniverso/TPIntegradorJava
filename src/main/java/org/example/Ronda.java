package org.example;
import java.util.ArrayList;
import java.util.Collection;

public class Ronda {

    private int id; // atributo
    private ArrayList<Partido> listaDePartidos; //atributo

    public Ronda(int id, ArrayList<Partido> listadepartidos) { // constructor
        this.id = id;
        this.listaDePartidos = listadepartidos;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Partido> getListaDePartidos() {
        return listaDePartidos;
    }

    public void agregarPartido (Partido partido){

        this.listaDePartidos.add(partido);
    }
    public Partido buscarPartidoPorId(int id) { // método que busca partido por Id
        for (Partido partido : this.listaDePartidos) { // Partido = clase , partido = elemento : partidos = lista -> por cada partido de partidos hacer
            if (partido.getId() == id) {
                return partido;
            }
        }
        return null; // si no encuentra el partido
    }
}

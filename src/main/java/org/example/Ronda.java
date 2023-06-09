package org.example;
import java.util.ArrayList;

public class Ronda {

    private int id; // atributo
    private ArrayList<Partido> listaDePartidos; //atributo

    public Ronda(int id) { // constructor
        this.id = id;
        this.listaDePartidos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Partido> getListaDePartidos() {
        return listaDePartidos;
    }

    public int cantidadPartidos(){
        return this.listaDePartidos.size();
    }

    public void agregarPartido (Partido partido) throws PartidoYaExisteException {
        for(Partido p: this.listaDePartidos){
            if(partido.esIgual(p)){
                throw new PartidoYaExisteException();
            }
        }
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

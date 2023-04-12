package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LectorDeArchivos {

    private String resultado; // atributo
    private String pronostico; //atributo

    public LectorDeArchivos(String resultado, String pronostico) { //constructor
        this.resultado = resultado;
        this.pronostico = pronostico;
    }

    public ArrayList<Ronda> leerResultados() { //método

        ArrayList<Ronda> listaDeRondas = new ArrayList<>(); // crea lista de rondas vacía

        // Leer el archivo de resultados
        BufferedReader reader1 = new BufferedReader(new FileReader(resultado));
        reader1.readLine(); // ignora el encabezado
        String linea;

        while ((linea = reader1.readLine()) != null) {
            String[] partes = linea.split(",");
            int ronda = Integer.parseInt(parts[0]);
            int id = Integer.parseInt(partes[1]); // se usa integer pq todo lo que se lee es string, transforma el string en un int
            int idEquipo1 = Integer.parseInt(partes[2]);
            String nombreEquipo1 = partes[3];
            int golesEquipo1 = Integer.parseInt(partes[4]);
            int golesEquipo2 = Integer.parseInt(partes[5]);
            int idEquipo2 = Integer.parseInt(partes[6]);
            String nombreEquipo2 = partes[7];

            Partido partido = new Partido(id, idEquipo1, idEquipo2, nombreEquipo1, nombreEquipo2, golesEquipo1, golesEquipo2);

            if (ronda==buscarRondaPorId(listaDeRondas, ronda).getId()) {
                buscarRondaPorId(listaDeRondas, ronda).agregarPartido(partido);
            }
            else {
                ArrayList<Partido> listaDePartidos = new ArrayList<>();
                Ronda unaronda = new Ronda(ronda, listaDePartidos);
                unaronda.agregarPartido(partido);
            }
        }
        reader1.close();
        return listaDeRondas;
    }

    private static Ronda buscarRondaPorId(ArrayList<Ronda> rondas, int id) { // método que busca ronda
        for (Ronda ronda : rondas) { // Ronda = clase , ronda = elemento : rondas = lista -> por cada ronda de rondas hacer...
            if (ronda.getId() == id){
                return ronda;
            }
        }
        throw new IllegalArgumentException("No se encontró ninguna ronda con el id " + id); // si no encuentra la ronda...
    }

    private static Persona buscarPersonaPorId(ArrayList<Persona> personas, int id) { // método que busca persona
        for (Persona persona : personas) { // Persona = clase , persona = elemento : personas = lista -> por cada persona de personas hacer...
            if (persona.getId() == id) {
                return persona;
            }
        }
        throw new IllegalArgumentException("No se encontró ninguna persona con el id " + id); // si no encuentra la persona...
    }

    public ArrayList<Persona> leerpronostico(ArrayList<Partido> listaDePartidos) {

        ArrayList<Persona> listaDePersonas = new ArrayList<>(); // crea lista de personas vacía

        BufferedReader reader = new BufferedReader(new FileReader(pronostico));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int idpersona = Integer.parseInt(parts[0]);
            String nombrepersona = parts[1];
            int idpartido = Integer.parseInt(parts[2]);
            int idGanador = Integer.parseInt(parts[4]);

            Partido partido = buscarPartidoPorId(listaDePartidos, idpartido);
            Pronostico pronostico = new Pronostico(partido, idGanador);

            if (idpersona==buscarPersonaPorId(listaDePersonas, idpersona).getId()) {
                buscarPersonaPorId(listaDePersonas, idpersona).agregarPronostico(pronostico);
            }
            else {
                ArrayList<Pronostico> listaDePronosticos = new ArrayList<>();
                Persona persona = new Persona(idpersona, nombrepersona, listaDePronosticos);
                persona.agregarPronostico(pronostico);
                listaDePersonas.add(persona);
            }
        }

        reader.close();
        return listaDePersonas;
    }

    private static Partido buscarPartidoPorId(Collection<Partido> partidos, int id) { // metodo que busca partido por id
        for (Partido partido : partidos) { // Partido = clase , partido = elemento : partidos = lista -> por cada partido de partidos hacer
            if (partido.getId() == id) {
                return partido;
            }
        }
        throw new IllegalArgumentException("No se encontró ninguna partido con el id " + id); // si no encuentra el partido
    }
}
package org.example;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException { // main que se ejecuta

        // Verificar si se ingresó un archivo como argumento
        if (args.length == 0) {
            throw new RuntimeException("ERROR: No ingresaste ningún archivo como argumento");
        }

        //el primer argumento debe ser la ruta de resultados, el segundo debe ser la ruta de pronósticos
        LectorDeArchivos lector = new LectorDeArchivos(args[0]); // creando instancia de la clase "lector de archivos"
        lector.leerResultados();
        ArrayList<Fase> fases = lector.getListaDeFases();
        LectorDB lectordb = new LectorDB(args[1]);
        lectordb.LeerPronostico(fases);
        ArrayList<Persona> personas = lectordb.getListaDePersonas();

        //imprimir partidos del archivo resultados.csv
        System.out.println("Partidos:");
        for (Fase fase : fases){
            System.out.println("Fase: " + fase.getId());
            for (Ronda ronda : fase.getListaDeRondas()) {
                System.out.println("Ronda: " + ronda.getId());
                for (Partido partido : ronda.getListaDePartidos()) {
                    System.out.println("Partido " + partido.getId() + ": " + partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
                }
            }
        }
        System.out.println("---------------------------");


        /*
        //agregar los partidos de cada ronda a una lista de partidos
        ArrayList<Partido> listaDePartidos = new ArrayList<>();
        for (Ronda ronda : listaDeRondas ) {
            listaDePartidos.addAll(ronda.getListaDePartidos());
        }
        //leer los pronósticos de todos los partidos y crear una lista de personas
        ArrayList<Persona> listaDePersonas = lector.leerPronostico(listaDePartidos);


        System.out.println("Puntajes de las rondas:");
        for (Ronda ronda : listaDeRondas) { // por cada ronda de la lista rondas
            //crea una lista de personas, pasándole la lista de partidos de la ronda al método leerPronostico
            ArrayList<Persona> personasDeLaRonda = lector.leerPronostico(ronda.getListaDePartidos()); // leer pronósticos de la ronda

            System.out.println("Ronda " + ronda.getId() + ": ");
            //para cada persona en una ronda, imprimir el puntaje
            for (Persona persona : personasDeLaRonda) {
                int puntajeDeLaRonda = persona.puntosPersona();
                System.out.println(persona.getNombre() + ": " + puntajeDeLaRonda);
            }
        }

        System.out.println("------------------------------");
        System.out.println("Puntajes totales:");
        for (Persona persona : listaDePersonas) { // por cada persona de la lista personas
            persona.setPuntajeTotal(persona.puntosPersona());
            // muestra el nombre de la persona con su respectivo puntaje
            System.out.printf("%s : %d \n", persona.getNombre(), persona.getPuntaje());
        }

        */
    }
}
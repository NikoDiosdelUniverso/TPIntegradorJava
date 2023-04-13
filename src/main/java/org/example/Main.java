package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException { // main que se ejecuta

        // Verificar si se ingresó un archivo como argumento
        if (args.length == 0) {
            throw new RuntimeException("ERROR: No ingresaste ningún archivo como argumento");
        }

        LectorDeArchivos lector = new LectorDeArchivos(args[0], args[1]); // creando instancia de la clase "lector de archivos"

        ArrayList<Ronda> listaDeRondas = lector.leerResultados(); // usando el método leer resultados
        ArrayList<Persona> listaDePersonas = new ArrayList<>(); // creando una lista de personas vacía

        for (Ronda ronda : listaDeRondas) { // por cada ronda de la lista rondas
            ArrayList<Persona> personasDeLaRonda = lector.leerpronostico(ronda.getListadepartidos()); // leer pronostico
            for (Persona persona : personasDeLaRonda) { // por cada persona de la lista personas
                int puntajeDelaRonda = persona.puntoPersona(); // le asigna el puntaje de la ronda a la persona
                persona.setPuntajeTotal(puntajeDelaRonda); // acumula total puntaje persona
                if ((LectorDeArchivos.buscarPersonaPorId(listaDePersonas, persona.getId())) == null) { // busco persona y si no esta en la lista la agrega
                    listaDePersonas.add(persona);
                }
            }
        }

        for (Persona persona : listaDePersonas) { // por cada persona de la lista personas
            System.out.printf(persona.getNombre(), " : ", persona.getPuntaje()); // muestra el nombre de la persona con su respectivo puntaje

        }
    }
}
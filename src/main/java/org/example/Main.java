package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Main {

    private static Partido buscarPartidoPorId(Collection<Partido> partidos, int id) { // metodo que busca partido por id
        for (Partido partido : partidos) { // Partido = clase , partido = elemento : partidos = lista -> por cada partido de partidos hacer
            if (partido.getId() == id) {
                return partido;
            }
        }
        throw new IllegalArgumentException("No se encontró ninguna partido con el id " + id); // si no encuentra el partido
    }

    public static void main(String[] args) throws IOException { // main que se ejecuta

        // Verificar si se ingresó un archivo como argumento
        if (args.length == 0) {
            throw new RuntimeException("ERROR: No ingresaste ningún archivo como argumento");
        }

        List<Partido> listaDePartidos = new ArrayList<>(); // crea lista de partidos vacia
        List<Pronostico> listaDePronosticos = new ArrayList<>(); // crea lista de pronostico vacia

        // Leer el archivo de resultados
        BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
        reader1.readLine(); // ignora el encabezado
        String linea;

        while ((linea = reader1.readLine()) != null) {
            String[] partes = linea.split(",");
            int id = Integer.parseInt(partes[0]); // se usa integer pq todo lo que se lee es string, transforma el string en un int
            int idEquipo1 = Integer.parseInt(partes[1]);
            String nombreEquipo1 = partes[2];
            int golesEquipo1 = Integer.parseInt(partes[3]);
            int golesEquipo2 = Integer.parseInt(partes[4]);
            int idEquipo2 = Integer.parseInt(partes[5]);
            String nombreEquipo2 = partes[6];

            Partido partido = new Partido(id, idEquipo1, idEquipo2, nombreEquipo1, nombreEquipo2, golesEquipo1, golesEquipo2);
            listaDePartidos.add(partido);
        }
        reader1.close();
/*
        //verificando que la lectura del archivo y la creacion de la lista de partidos sea correcta
        System.out.println("Resumen de partidos:");
        for (Partido partido : listaDePartidos) {
            System.out.println("Id partido: " + partido.getId() + ", Id equipo 1: " + partido.getIdEquipo1() + ", Id equipo 2: " + partido.getIdEquipo2() + ", Id ganador: " + partido.Ganador());
        }
 */
        // Leer el archivo de pronosticos
        BufferedReader reader = new BufferedReader(new FileReader(args[1]));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int idGanador = Integer.parseInt(parts[2]);

            Partido partido = buscarPartidoPorId(listaDePartidos, id);
            Pronostico pronostico = new Pronostico(partido, idGanador);
            listaDePronosticos.add(pronostico);
        }
        reader.close();
/*
        //verificando que la lectura del archivo y la creacion de la lista de pronosticos sea correcta
        System.out.println("Resumen de pronosticos:");
        for (Pronostico pronostico : listaDePronosticos) {

            System.out.println("Id partido: " + pronostico.getId() + ", Id equipo 1: " + pronostico.getIdEquipo1() + ", Id equipo 2: " + pronostico.getIdEquipo2() + ", Id ganador: " + pronostico.getIdGanador());
        }
 */

        int puntuacion = 0;
        for (Pronostico pronostico : listaDePronosticos) {
            puntuacion += pronostico.Puntos();
        }

        System.out.println("Puntaje: " + puntuacion);
        //devolver el puntaje de la persona
    }
}
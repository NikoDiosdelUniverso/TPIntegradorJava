package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;


public class Main {

    // imprimir partidos del archivo resultados.csv
    private static void imprimirResultados(ArrayList<Fase> fases) {

        System.out.println("--------+-------+---------+--------------------------------");
        System.out.printf("| %-5s | %-5s | %-7s | %-30s |\n", "Fase", "Ronda", "Partido", "Equipos");
        System.out.println("--------+-------+---------+--------------------------------");
        for (Fase fase : fases) {
            for (Ronda ronda : fase.getListaDeRondas()) {
                for (Partido partido : ronda.getListaDePartidos()) {
                    System.out.printf("| %-5s | %-5s | %-7s | %-30s |\n",
                            fase.getId(), ronda.getId(), partido.getId(),
                            partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
                }
            }
        }
        System.out.println("--------+-------+---------+--------------------------------");
    }

    // imprimir los aciertos de las personas
    public static void imprimirAciertos(ArrayList<Persona> personas) {

        System.out.println("\u001B[32m-----------------------------------------------------");
        System.out.printf("%-10s | %-10s\n", "Persona", "Aciertos totales");
        for (Persona persona : personas) {
            System.out.printf("%-10s | %-10s\n", persona.getNombre(), persona.puntosPersona(1));
        }
        System.out.println("-----------------------------------------------------\n");
    }

    private static int[] leerConfigPuntos(String rutaConfig) throws IOException {
        // leer el archivo de configuracion de puntos
        int[] valores = new int[3];
        BufferedReader reader = new BufferedReader(new FileReader(rutaConfig));
        reader.readLine(); // ignora el encabezado
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(";");
            int puntosPartido = Integer.parseInt(partes[0]);
            int puntosRonda = Integer.parseInt(partes[1]);
            int puntosFase = Integer.parseInt(partes[2]);
            valores = new int[]{puntosPartido, puntosRonda, puntosFase};
        }
        reader.close();
        return valores;
    }

    private static int calcularPuntajeDeLaRonda(Persona persona, Ronda ronda) {
        int puntajeDeLaRonda = 0;
        for (Partido partido : ronda.getListaDePartidos()) {
            Pronostico pronostico = persona.buscarPronostico(partido);
            if (pronostico != null) {
                puntajeDeLaRonda += pronostico.Puntos();
            }
        }
        return puntajeDeLaRonda;
    }

    // Calcula los puntos extra
    private static int calcularPuntosExtra(Persona persona, ArrayList<Fase> fases, int puntosRonda, int puntosFase) {
        int puntosExtra = 0;
        for (Fase fase : fases) {
            int puntajeDeLaFase = 0;
            int cantidadPartidosDeLaFase = 0;
            for (Ronda ronda : fase.getListaDeRondas()) {
                cantidadPartidosDeLaFase += ronda.cantidadPartidos();
                int puntajeDeLaRonda = calcularPuntajeDeLaRonda(persona, ronda);
                puntajeDeLaFase += puntajeDeLaRonda;
                if (puntajeDeLaRonda == ronda.cantidadPartidos()) {
                    puntosExtra += puntosRonda;
                }
            }
            if (puntajeDeLaFase == cantidadPartidosDeLaFase) {
                puntosExtra += puntosFase;
            }
        }
        return puntosExtra;
    }

    // Metodo calcular puntos. Imprime los participantes con sus puntajes
    private static void imprimirPuntajeTotal(ArrayList<Persona> personas, ArrayList<Fase> fases, int[] puntos) {
        int puntosPartido = puntos[0];
        int puntosRonda = puntos[1];
        int puntosFase = puntos[2];

        // imprimir puntos extra
        System.out.println("\u001B[34m-----------------------------------------------------");
        System.out.printf("%-10s | %-10s\n", "Persona", "Puntos Extra");
        for (Persona persona : personas) {
            int puntosExtra = calcularPuntosExtra(persona, fases, puntosRonda, puntosFase);
            System.out.printf("%-10s | %-10s\n", persona.getNombre(), puntosExtra);
            persona.addPuntaje(puntosExtra);
        }
        System.out.println("-----------------------------------------------------\n");

        //imprimir puntajes totales
        System.out.println("\u001B[36m-----------------------------------------------------");
        System.out.printf("%-10s | %-10s\n", "Persona", "Puntaje total");
        for (Persona persona : personas) {
            persona.addPuntaje(persona.puntosPersona(puntosPartido));
        }
        //ordenar por puntaje
        personas.sort(Comparator.comparingInt(Persona::getPuntaje).reversed());
        for (Persona persona : personas) {
            System.out.printf("%-10s | %-10s\n", persona.getNombre(), persona.getPuntaje());
        }
        System.out.println("-----------------------------------------------------\n");
    }

    public static void main(String[] args) throws IOException { // main que se ejecuta

        // Verificar si se ingresó un archivo como argumento
        if (args.length == 0) {
            throw new RuntimeException("ERROR: No ingresaste ningún archivo como argumento");
        }

        //el primer argumento debe ser la ruta de resultados
        LectorDeArchivos lector = new LectorDeArchivos(args[0]); // creando instancia de la clase "lector de archivos"
        lector.leerResultados();
        ArrayList<Fase> fases = lector.getListaDeFases();
        //el segundo argumento debe ser la ruta de configuracion de base de datos
        LectorDB lectordb = new LectorDB(args[1]);
        lectordb.LeerPronostico(fases);
        ArrayList<Persona> personas = lectordb.getListaDePersonas();

        imprimirResultados(fases);
        imprimirAciertos(personas);

        //imprimir los puntajes totales
        //el tercer argumento debe ser la ruta del archivo de configuracion de puntos
        imprimirPuntajeTotal(personas, fases, leerConfigPuntos(args[2]));


    }
}
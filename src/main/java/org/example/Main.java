package org.example;

import java.io.*;
import java.util.ArrayList;


public class Main {

    //metodo calcular puntos. recibe el archivo de configuracion, devuelve los participantes con sus puntajes
private static void calcularPuntaje(ArrayList<Persona> personas, ArrayList<Fase> fases, String rutaConfig) throws IOException {
    //leer el archivo de configuracion de puntos
    int puntosPartido = 0;
    int puntosRonda = 0;
    int puntosFase = 0;
    BufferedReader reader = new BufferedReader(new FileReader(rutaConfig));
    reader.readLine(); // ignora el encabezado
    String linea;
    while ((linea = reader.readLine()) != null) {
        String[] partes = linea.split(";");
        puntosPartido = Integer.parseInt(partes[0]);
        puntosRonda = Integer.parseInt(partes[1]);
        puntosFase = Integer.parseInt(partes[2]);
    }
    reader.close();

    //sumar puntos extra por ronda completa acertada
    //sumar puntos extra por fase entera acertada
    System.out.println("Puntos Extra:");
    for (Persona p : personas){
        int puntosExtra = 0;
        for (Fase f : fases){
            int puntajeDeLaFase = 0;
            int cantidadPartidosDeLaFase = 0;
            for (Ronda ronda : f.getListaDeRondas()) {
                //sumar los partidos de las rondas para obtener la cantidad de partidos de una fase.
                cantidadPartidosDeLaFase += ronda.getListaDePartidos().size();
                int puntajeDeLaRonda = 0;
                //para cada persona, si el puntaje de la ronda es igual a la cantidad de partidos, sumar puntos extra
                for (Partido partido : ronda.getListaDePartidos()){
                    //suma los puntos de cada partido de la ronda
                    puntajeDeLaRonda += p.buscarPronostico(partido).Puntos();
                }
                if(puntajeDeLaRonda == ronda.getListaDePartidos().size()){
                    puntosExtra += puntosRonda;
                }
                puntajeDeLaFase += puntajeDeLaRonda;
            }
            if (puntajeDeLaFase == cantidadPartidosDeLaFase){
                puntosExtra += puntosFase;
            }
        }
        System.out.println(p.getNombre()+ ": " + puntosExtra);
       p.addPuntaje(puntosExtra);
    }
    System.out.println("---------------------------");
    //imprimir puntajes totales
    System.out.println("Puntajes totales: ");
    for (Persona persona: personas){
        persona.addPuntaje(persona.puntosPersona(puntosPartido));
        System.out.println(persona.getNombre()+": "+persona.getPuntaje());
    }
}

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


        //imprimir los aciertos de las personas
        System.out.println("Aciertos totales: ");
        for (Persona persona: personas){
            System.out.println(persona.getNombre()+": "+persona.puntosPersona(1));
        }
        System.out.println("---------------------------");

        //imprimir los puntajes totales
      calcularPuntaje(personas,fases, args[2]);

        //todo: metodo calcular puntaje
        //agregar partidos, personas, etc
        //excepciones
        //test




        /*
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
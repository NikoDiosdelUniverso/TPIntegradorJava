package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;


public class Main {
    public static void main(String[] args) {

        // Verificar si se ingresó un archivo como argumento
        if(args.length == 0){
            System.out.println("ERROR: No ingresaste ningún archivo como argumento!");
            System.exit(88);
        }

        // Leer partidos desde un archivo csv
        List<Partido> listaDePartidos;
        try {
            listaDePartidos = new CsvToBeanBuilder(new FileReader(args[0]))
                    .withType(Partido.class).build().parse();
            // Imprimir una representacion de cada partido como String
            for (Partido partido : listaDePartidos) {
                System.out.println(partido.toString());
            }
            // Imprimir información de la excepción si ocurre un error al leer el archivo
            } catch (IOException e) {
            e.printStackTrace();
              }

        // Leer pronosticos desde un archivo csv
        List<Pronostico> listaDePronosticos;
        try {
             listaDePronosticos = new CsvToBeanBuilder(new FileReader(args[1]))
                        .withType(Pronostico.class)
                        .build()
                        .parse();
            // Imprimir una representacion de cada pronostico como String
             for (Pronostico pronostico : listaDePronosticos) {
                    System.out.println(pronostico.toString());
                }
            // Imprimir información de la excepción si ocurre un error al leer el archivo
            } catch (IOException e) {
                e.printStackTrace();
                }


    //devolver el puntaje de la persona
}}
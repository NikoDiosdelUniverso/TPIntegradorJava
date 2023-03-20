package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;


public class Main {
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("ERROR: No ingresaste ningún archivo como argumento!");
            System.exit(88);
        }

        List<Partido> listaDePartidos;
        try {
            listaDePartidos = new CsvToBeanBuilder(new FileReader(args[0]))
                    .withType(Partido.class).build().parse();
            for (Partido partido : listaDePartidos) {
                System.out.println(partido.toString());
            }

            } catch (IOException e) {
            e.printStackTrace();
              }

        List<Pronostico> listaDePronosticos;
        try {
             listaDePronosticos = new CsvToBeanBuilder(new FileReader(args[1]))
                        .withType(Pronostico.class)
                        .build()
                        .parse();

             for (Pronostico pronostico : listaDePronosticos) {
                    System.out.println(pronostico.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                }


    //tomar como argumento las rutas a los archivos
    //instanciar objetos de las clases
    //devolver el puntaje de la persona
}}
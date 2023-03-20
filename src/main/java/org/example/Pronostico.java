package org.example;

import com.opencsv.bean.CsvBindByName;

public class Pronostico {
    @CsvBindByName(column = "id partido")
    private int id;

    @CsvBindByName(column = "Equipo 1", required = true)
    private int idEquipo1;

    @CsvBindByName(column = "Ganador", required = true)
    private int idGanador;
    @CsvBindByName(column = "Equipo 2", required = true)
    private int idEquipo2;

    Partido partido;

    //suma un punto por cada pronostico acertado
    public int Puntos(){
        int puntos = 0;
        if (partido.Ganador() == this.idGanador) {
            puntos += 1;
        }
        return puntos;
    }
}

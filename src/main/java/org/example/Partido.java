package org.example;

import com.opencsv.bean.CsvBindByName;

public class Partido {

    @CsvBindByName(column = "id partido")
    private int id;
    @CsvBindByName(column = "Equipo 1 - id", required = true)
            int idEquipo1;
    @CsvBindByName(column = "Equipo 1 - nombre", required = true)
            String nombreEquipo1;
    @CsvBindByName(column = "Equipo 1 - cantidad goles", required = true)
            int golesEquipo1;
    @CsvBindByName(column = "Equipo 2 - cantidad goles", required = true)
    int golesEquipo2;
    @CsvBindByName(column = "Equipo 2 - id", required = true)
    int idEquipo2;
    @CsvBindByName(column = "Equipo 2 - nombre", required = true)
    String nombreEquipo2;

    Equipo equipo1 = new Equipo(idEquipo1, nombreEquipo1);
    Equipo equipo2 = new Equipo(idEquipo2, nombreEquipo2);


public String getPartido() {
    return "Partido numero " + this.id + ":" + this.equipo1 + " vs " + this.equipo2;
}
    //compara los puntajes de los equipos y devuelve el ganador
    public int Ganador() {
        if (golesEquipo1 > golesEquipo2) {
            return equipo1.getId();
        } else if (golesEquipo2 > golesEquipo1) {
            return equipo2.getId();
        } else {
            return  0;
        }
    }
}

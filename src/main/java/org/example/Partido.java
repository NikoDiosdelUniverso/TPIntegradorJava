package org.example;


public class Partido {

    private int id;
    private int idEquipo1;
    private String nombreEquipo1;
    private int golesEquipo1;
    private int golesEquipo2;
    private int idEquipo2;
    private String nombreEquipo2;
    private Equipo equipo1;

    private Equipo equipo2;

    public Partido(int id, int idEquipo1, int idEquipo2, String nombreEquipo1, String nombreEquipo2, int golesEquipo1, int golesEquipo2) {
        this.id = id;
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        this.nombreEquipo1 = nombreEquipo1;
        this.nombreEquipo2 = nombreEquipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.equipo1 = new Equipo(idEquipo1, nombreEquipo1);
        this.equipo2 = new Equipo(idEquipo2, nombreEquipo2);
    }

    public int getId() {
        return id;
    }
    public int getIdEquipo1() {
        return idEquipo1;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    //compara los puntajes de los equipos y devuelve el ganador
    public int Ganador() {
        if (golesEquipo1 > golesEquipo2) {
            return idEquipo1;
        } else if (golesEquipo2 > golesEquipo1) {
            return idEquipo2;
        } else {
            return 0;
        }
    }

}

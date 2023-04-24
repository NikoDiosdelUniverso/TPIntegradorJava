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

    public Partido(int id, Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.id = id;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.idEquipo1 = equipo1.getId();
        this.idEquipo2 = equipo2.getId();
        this.nombreEquipo1 = equipo1.getNombre();
        this.nombreEquipo2 = equipo2.getNombre();
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public boolean esIgual(Partido partido){
        return (partido.id == this.id || partido.tieneEquipos(this.nombreEquipo1, this.nombreEquipo2));
    }
    public boolean tieneEquipos(String equipo1, String equipo2){
        return (
                (this.nombreEquipo1.equals(equipo1) && this.nombreEquipo2.equals(equipo2)) ||
                (this.nombreEquipo1.equals(equipo2) && this.nombreEquipo2.equals(equipo1))
                );
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

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

}

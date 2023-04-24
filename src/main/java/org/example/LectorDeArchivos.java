package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorDeArchivos {

    private String resultado; // atributo
    private ArrayList<Equipo> ListaDeEquipos; // atributo
    private ArrayList<Fase> ListaDeFases; // atributo
    private ArrayList<Ronda> ListaDeRondas; // atributo
    private ArrayList<Partido> listaDePartidos;

    public LectorDeArchivos(String resultado) { //constructor
        this.resultado = resultado;
        this.ListaDeEquipos = new ArrayList<>();
        this.ListaDeFases = new ArrayList<>();
        this.ListaDeRondas = new ArrayList<>();
    }

    public ArrayList<Fase> getListaDeFases() { //getter
        return ListaDeFases;
    }

    private Ronda buscarRondaPorId(int id) { // método que busca ronda
        for (Ronda ronda : this.ListaDeRondas) { // Ronda = clase , ronda = elemento : rondas = lista -> por cada ronda de rondas hacer...
            if (ronda.getId() == id) {
                return ronda;
            }
        }
        return null; // si no encuentra la ronda...
    }

    // Agrega un partido a una ronda. Si la ronda no existe, la crea y la agrega a la lista de rondas.
    private Ronda agregarRondas(int ronda, Partido partido) throws PartidoYaExisteException {
        if (buscarRondaPorId(ronda) == null) {
            Ronda unaronda = new Ronda(ronda);
            unaronda.agregarPartido(partido);
            this.ListaDeRondas.add(unaronda);
            return unaronda;
        } else {
            buscarRondaPorId(ronda).agregarPartido(partido);
            return buscarRondaPorId(ronda);
        }
    }

    // Busca el equipo con el id dado
    private Equipo buscarEquipoPorId(int id) {
        for (Equipo equipo : this.ListaDeEquipos) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }

    // Crea un equipo con los parámetros dados solo si no existe y lo agrega a la lista dada. Devuelve el equipo.
    private Equipo agregarEquipos(int id, String nombre) {
        if (buscarEquipoPorId(id) == null) {
            Equipo equipo = new Equipo(id, nombre);
            this.ListaDeEquipos.add(equipo);
            return equipo;
        } else {
            return buscarEquipoPorId(id);
        }
    }

    public Fase BuscarFase(int id) { // método para buscar fase por id
        for (Fase f : ListaDeFases) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public Fase AgregarFase(int id) { //método para agregar fase

        if (BuscarFase(id) == null) {
            Fase fase = new Fase(id);
            this.ListaDeFases.add(fase);
            return fase;
        }
        return BuscarFase(id);
    }

    public void agregarPartido(Partido p) throws PartidoYaExisteException {
        if (this.listaDePartidos == null) {
            listaDePartidos = new ArrayList<>();
        }
        for (Partido partido : this.listaDePartidos) {
            if (partido.esIgual(p)) {
                throw new PartidoYaExisteException();
            }
        }
            this.listaDePartidos.add(p);
    }

    public ArrayList<Ronda> leerResultados() throws IOException { //método para leer el archivo de resultados

        // Leer el archivo de resultados
        BufferedReader reader1 = new BufferedReader(new FileReader(resultado));
        reader1.readLine(); // ignora el encabezado
        String linea;

        while ((linea = reader1.readLine()) != null) {
            String[] partes = linea.split(",");
            int fase = Integer.parseInt(partes[0]);
            int ronda = Integer.parseInt(partes[1]);
            int id = Integer.parseInt(partes[2]); // se usa integer pq lo que se lee es string, transforma el string en un int
            int idEquipo1 = Integer.parseInt(partes[3]);
            String nombreEquipo1 = partes[4];
            int golesEquipo1 = Integer.parseInt(partes[5]);
            int golesEquipo2 = Integer.parseInt(partes[6]);
            int idEquipo2 = Integer.parseInt(partes[7]);
            String nombreEquipo2 = partes[8];

            Equipo equipo1 = agregarEquipos(idEquipo1, nombreEquipo1); // crea los equipos
            Equipo equipo2 = agregarEquipos(idEquipo2, nombreEquipo2); // crea los equipos

            Partido partido = new Partido(id, equipo1, equipo2, golesEquipo1, golesEquipo2); // crea el partido
            try {
                agregarPartido(partido);
                Ronda unaronda = agregarRondas(ronda, partido); // agrega los partidos a la ronda

                Fase unafase = AgregarFase(fase);

                unafase.agregarRondaALaFase(unaronda);
            } catch (PartidoYaExisteException e) {
                System.out.println("Ya existe el partido con los equipos " + nombreEquipo1 + " y " + nombreEquipo2);
                e.printStackTrace();
            }
        }
        reader1.close();
        return null;
    }
}
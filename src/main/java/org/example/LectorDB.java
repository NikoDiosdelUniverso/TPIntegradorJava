package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Collection;

public class LectorDB {

    private ArrayList<Persona>ListaDePersonas; // atributo
    private ArrayList<Pronostico>ListaDePronosticos; // atributo
    private String rutaConfiguracion; // atributo

    public ArrayList<Pronostico> getListaDePronosticos() { // getter
        return ListaDePronosticos;
    }

    public ArrayList<Persona> getListaDePersonas() { // getter
        return ListaDePersonas;
    }

    public LectorDB(String rutaConfiguracion) {
        this.rutaConfiguracion = rutaConfiguracion;
        this.ListaDePronosticos = new ArrayList<>();
        this.ListaDePersonas = new ArrayList<>();
    }

    public void LeerPronostico (ArrayList<Fase>ListaDeFases) throws IOException {
        Connection conexion = null;
        Statement consulta = null;
        ConectorSQL conector = new ConectorSQL(rutaConfiguracion);
        conector.leerconfiguracion();
        try {
            conexion = DriverManager.getConnection(conector.getUrl(), conector.getUsuario(), conector.getContrasenia());
            // Ejecutar una consulta
            consulta = conexion.createStatement();
            String sql;
            sql = "SELECT id, fase, ronda, partido, equipo1, ganador, equipo1, persona FROM pronostico";

            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            ResultSet resultado = consulta.executeQuery(sql);

            // Obtener las distintas filas de la consulta
            while (resultado.next()) {
                // Obtener el valor de cada columna
                int id = resultado.getInt("id");
                int fase = resultado.getInt("fase");
                int ronda = resultado.getInt("ronda");
                int Partido = resultado.getInt("partido");
                String equipo1 = resultado.getString("equipo1");
                int ganador = resultado.getInt("ganador");
                String equipo2 = resultado.getString("equipo2");
                String persona = resultado.getString("persona");
                int idpersona = resultado.getInt("idpersona");
                Fase unafase = BuscarFase(ListaDeFases, fase);
                Ronda unaRonda = unafase.buscarRondaPorId(ronda);
                Partido unPartido = unaRonda.buscarPartidoPorId(Partido);
                Pronostico pronostico = new Pronostico(unPartido, ganador);
                this.ListaDePronosticos.add(pronostico);
                agregarPersonas(idpersona, persona, pronostico);
            }
            // Esto se utiliza par cerrar la conexión con la base de datos
            resultado.close();
            consulta.close();
            conexion.close();

        } catch ( // Excepción ante problemas de conexión
    SQLException se) {

        se.printStackTrace();
    } finally {
        // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
        try {
            if (consulta != null)
                consulta.close();
        } catch (SQLException se2) {
        }
        try {
            if (conexion != null)
                conexion.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    }

    public Fase BuscarFase (ArrayList<Fase> ListaDeFases, int id){ // método para buscar fase por id
        for (Fase f : ListaDeFases){
            if (f.getId() == id){
                return f;
            }
        }
        return null;
    }

    public Persona buscarPersonaPorId(int id) { // método que busca persona
        for (Persona persona : this.ListaDePersonas) { // Persona = clase , persona = elemento : personas = lista -> por cada persona de personas hacer...
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

    // Agrega un pronóstico a la persona. Si la persona no existe, la crea y la agrega a la lista de personas.
    private void agregarPersonas(int idpersona, String nombrepersona, Pronostico pronostico) {
        if (buscarPersonaPorId(idpersona) == null) {
            ArrayList<Pronostico> listaDePronosticos = new ArrayList<>();
            Persona persona = new Persona(idpersona, nombrepersona, listaDePronosticos);
            persona.agregarPronostico(pronostico);
            this.ListaDePersonas.add(persona);
        } else {
            buscarPersonaPorId(idpersona).agregarPronostico(pronostico);
        }
    }

}

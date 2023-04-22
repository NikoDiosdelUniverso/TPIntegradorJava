import org.example.Equipo;
import org.example.Partido;
import org.example.Persona;
import org.example.Pronostico;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PersonaTest {
    private Persona persona; // atributo
    private Pronostico pronostico; // atributo
    private Partido partido1; // atributo

    @Before

    public void init (){
        Equipo equipo1 = new Equipo(1, "Equipo 1");
        Equipo equipo2 = new Equipo(2, "Equipo 2");
        this.partido1 = new Partido(1, equipo1, equipo2,2, 0);
    }

    @Test
    public void sinpronostico (){
        this.persona = new Persona(1,"nicolas",new ArrayList<>());
        assertEquals(this.persona.puntosPersona(1),0);
}

    @Test
    public void pronosticoacertado (){
        this.persona = new Persona(1,"nicolas",new ArrayList<>());
        this.pronostico = new Pronostico(partido1,1);
        this.persona.agregarPronostico(this.pronostico);
        assertEquals(this.persona.puntosPersona(1),1);
        assertEquals(this.persona.puntosPersona(2),2);
    }

    @Test
    public void pronosticonoacertado (){
        this.persona = new Persona(1,"nicolas",new ArrayList<>());
        this.pronostico = new Pronostico(partido1,0);
        this.persona.agregarPronostico(this.pronostico);
        assertEquals(this.persona.puntosPersona(1),0);
        assertEquals(this.persona.puntosPersona(2),0);
    }
}





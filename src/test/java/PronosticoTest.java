import org.example.Equipo;
import org.example.Partido;
import org.example.Pronostico;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PronosticoTest {
    private Pronostico pronostico;
    private Partido partido;
    private Equipo equipo1;
    private Equipo equipo2;
    private Pronostico pronostico1;

    @Before

    public void init(){
        this.equipo1 = new Equipo(1, "Equipo 1");
        this.equipo2 = new Equipo(2, "Equipo 2");
        this.partido = new Partido(1, equipo1, equipo2, 2,0);
    }

    @Test

    public void aciertaPronostico(){

        this.pronostico = new Pronostico(this.partido, 1);

        assertEquals(this.pronostico.Puntos(), 1);

    }

    @Test

    public  void fallaPronostico(){

        this.pronostico = new Pronostico(this.partido, 2);
        this.pronostico1 = new Pronostico (this.partido,0);

        assertEquals(this.pronostico.Puntos(), 0);
        assertEquals(this.pronostico1.Puntos(), 0);

    }


}

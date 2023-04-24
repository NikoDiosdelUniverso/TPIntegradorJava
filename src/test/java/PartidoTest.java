
import org.example.Equipo;
import org.example.Partido;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PartidoTest {
    private Equipo equipo1;
    private Equipo equipo2;

    @Before

    public void init(){
        this.equipo1 = new Equipo(1, "Equipo 1");
        this.equipo2 = new Equipo(2, "Equipo 2");
    }

    @Test

    public void ganadorEquipo1(){
        Partido partido1 = new Partido(1, equipo1, equipo2,2, 0);

        assertEquals(partido1.Ganador(), 1); //assertEquals le pasas dos argumentos y verifica que esas cosas sean igual (valor real y valor esperado)
    }

    @Test
    public void ganadorEquipo2(){
        Partido partido2 = new Partido(1, equipo1, equipo2,1, 3);

        assertEquals(partido2.Ganador(), 2); //assertEquals le pasas dos argumentos y verifica que esas cosas sean igual (valor real y valor esperado)
    }

    @Test
    public void ganadorEmpate(){
        Partido partido3 = new Partido(1, equipo1, equipo2,0, 0);

        assertEquals(0, partido3.Ganador()); //assertEquals le pasas dos argumentos y verifica que esas cosas sean igual (valor real y valor esperado)
    }


}

package terreno;

import com.company.excepciones.CasilleroLlenoException;
import com.company.modelo.terreno.Casillero;
import com.company.modelo.unidades.Aldeano;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CasilleroTest {

    @Test
    public void casilleroRecienCreadoEstaVacioTest(){
        Casillero casillero = new Casillero();
        assertFalse(casillero.estaOcupado());
    }

    @Test
    public void casilleroVacioPermiteInsercionTest(){
        Casillero casillero = new Casillero();
        Aldeano aldeano = new Aldeano();

        try {
            casillero.agregarPosicionable(aldeano);
        } catch (CasilleroLlenoException e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void casilleroEstaLlenoLuegoDeInsercionTest(){
        Casillero casillero = new Casillero();
        Aldeano aldeano = new Aldeano();

        try {
            casillero.agregarPosicionable(aldeano);
        } catch (CasilleroLlenoException e) {
            assertTrue(false);
        }
        assertTrue(casillero.estaOcupado());
    }

    @Test
    public void luegoDeQuitarPosicionableElCasilleroEstaVacioNuevamenteTest(){
        Casillero casillero = new Casillero();
        Aldeano aldeano = new Aldeano();

        try {
            casillero.agregarPosicionable(aldeano);
        } catch (CasilleroLlenoException e) {
            assertTrue(false);
        }
        casillero.quitarPosicionable();
        assertFalse(casillero.estaOcupado());
    }

}

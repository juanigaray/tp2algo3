package com.company.modelo.edificios;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.Jugador;
import com.company.modelo.Posicion;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.estados.EstadoEdificio;
import com.company.modelo.edificios.estados.EstadoEdificioEnConstruccion;
import com.company.modelo.edificios.estados.EstadoPorConstruir;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

import java.util.ArrayList;

public abstract class Edificio extends Posicionable {

    protected static Integer VIDA_MAXIMA;
    protected static Integer COSTO;
    protected static Integer MONTO_DE_REPARACION;
    protected static Integer BLOQUES_DE_ANCHO ;
    protected static Integer BLOQUES_DE_ALTO;
    protected static Integer vidaActual;

    protected ArrayList<Posicion> posiciones;
    protected EstadoEdificio estado;
    protected Jugador jugador;

    public Edificio(Jugador jugador){

        posiciones = new ArrayList<>();

        this.jugador = jugador;

        this.estado = new EstadoPorConstruir(VIDA_MAXIMA,COSTO);
    }

	@Override
	public void recibirDanio(Integer unDanio) throws Exception {
		estado = estado.recibirDanio(unDanio);
	}

    public void construir(Aldeano quienLoConstruye, Integer posicionHorizontal, Integer posicionVertical)
            throws EdificioEnConstruccionException, Exception {

        /*TODO: manejo de posiciones.*/

        jugador.cobrar(this.COSTO);

        this.ubicar(posicionHorizontal,posicionVertical);


        estado = estado.construir(quienLoConstruye);
    }

    public void suspender(Aldeano quienLoConstruye) throws Exception {
        estado = estado.suspender(quienLoConstruye);
    }

    @Override
    public void ubicar(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException {

        for( int i = posicionHorizontal; i < ( posicionHorizontal + BLOQUES_DE_ANCHO ); i++)
            for (int j = posicionVertical; j < (posicionVertical + BLOQUES_DE_ALTO); j++) {
                Posicion posicion = new Posicion(i, j);

                posicion.posicionar(this);
                posiciones.add(posicion);
            }

    }

    public Integer getVida() {
        return this.vidaActual;
    }

    public abstract void crear(Unidad unidad) throws CasilleroNoExistenteException, CasilleroLlenoException;
    
    public void reparar(Aldeano reparador) throws Exception {
       this.estado = this.estado.reparar(reparador, MONTO_DE_REPARACION);
    }

    public void eliminar() throws CasilleroNoExistenteException {
        int longitud = posiciones.size();

        for (int i = longitud - 1; i >= 0; i--) {
            Posicion posicion = posiciones.remove(i);
            posicion.eliminar();
        }
    }

}

package com.company.modelo.unidades;

import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.MovimientoInvalidoException;
import com.company.modelo.Jugador;
import com.company.modelo.Posicion;
import com.company.modelo.Posicionable;
import com.company.modelo.unidades.estados.EstadoUnidad;
import com.company.modelo.unidades.estados.EstadoUnidadInactivo;

public abstract class Unidad extends Posicionable {

    protected Posicion posicion;
    protected EstadoUnidad estado;

    protected Integer COSTO;

    public Unidad(Jugador jugador) {
        this.jugador = jugador;
        this.posicion = null;
    }


    public Integer getCosto(){
        return this.COSTO;
    }

    public Integer getVida(){
        return estado.getVidaActual();
    }

    public EstadoUnidad establecerEstadoInicial(Integer vidaMax, Integer vidaActual){
        return new EstadoUnidadInactivo(vidaMax,vidaActual);
    }

    public void establecerCoordenadasDeNacimiento(Integer posicionHorizontal, Integer posicionVertical) {
        posicion = new Posicion(posicionHorizontal, posicionVertical);
    }

    public void moverA(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException,
            ArmaMontadaException, MovimientoInvalidoException {

        posicion.moverA(posicionHorizontal, posicionVertical);
    }

    public void recibirDanio(Integer montoDeDanio) throws Exception {
        try {
            estado.recibirDanio(montoDeDanio);
        } catch (Exception | EdificioEnConstruccionException ignored) {
        }
    }

    private void eliminarDePosicion() {
        if (posicion != null) {
            posicion.quitarPosicionable();
        }
    }

    @Override
    public void ubicar(Integer posicionHorizontal, Integer posicionVertical) throws CasilleroNoExistenteException, CasilleroLlenoException {
        posicion.posicionar(this);
    }

    public void actualizar() throws Exception {
        estado = estado.actualizar();
    }
}

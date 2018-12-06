package com.company.modelo;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.EdificioDestruidoExcepcion;
import com.company.excepciones.UnidadMuertaException;

public abstract class Posicionable {

    protected Jugador jugador;

    // Queda abstracto porque los edificios y las unidades se ubican de formas distintas
    public abstract void ubicar(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException;

    public abstract void recibirDanio(Integer montoDeDanio)
            throws Exception, EdificioEnConstruccionException, EdificioDestruidoExcepcion, UnidadMuertaException;

    public abstract Integer getVida() throws Exception;

    public Jugador getJugador(){
        return this.jugador;
    }
}


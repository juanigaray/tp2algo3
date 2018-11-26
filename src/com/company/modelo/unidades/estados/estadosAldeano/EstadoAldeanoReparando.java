package com.company.modelo.unidades.estados.estadosAldeano;

import com.company.excepciones.AldeanoOcupadoException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.EdificioDestruidoExcepcion;
import com.company.excepciones.EdificioNoConstruidoException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Edificio;

public class EstadoAldeanoReparando extends EstadoAldeano {

    public EstadoAldeanoReparando(){
        super(50);
    }

    @Override
    public EstadoAldeano otorgarGanancia(Jugador jugador) {
        throw new AldeanoOcupadoException("... reparando");
    }

    @Override
    public EstadoAldeano construir(Edificio edificio, Integer posicionH, Integer posicionV) throws EdificioEnConstruccionException {
        throw new AldeanoOcupadoException("Estoy reparando...");
    }

    @Override
    public EstadoAldeano reparar(Edificio aReparar) throws EdificioEnConstruccionException, EdificioNoConstruidoException, EdificioDestruidoExcepcion {

        if(edificioATrabajar == null) edificioATrabajar = aReparar;

        else if(edificioATrabajar != aReparar){
            edificioATrabajar.suspenderConstruccion();
            edificioATrabajar = aReparar;
        }

        return this;
    }

}
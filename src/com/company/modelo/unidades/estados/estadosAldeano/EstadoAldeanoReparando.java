package com.company.modelo.unidades.estados.estadosAldeano;

import com.company.excepciones.AldeanoOcupadoException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.Aldeano;

public class EstadoAldeanoReparando extends EstadoAldeano {

   public EstadoAldeanoReparando(Aldeano aldeano){
        super(aldeano);
   }

    @Override
    public void otorgarGanancia(Jugador jugador) {
        throw new AldeanoOcupadoException("... reparando");
    }

    @Override
    public EstadoAldeano construir(Edificio edificio, Integer posicionH, Integer posicionV) throws Exception, EdificioEnConstruccionException {
        throw new AldeanoOcupadoException("Estoy reparando...");
    }

    @Override
    public EstadoAldeano reparar(Edificio aReparar) throws Exception {
        if(edificioATrabajar == null){
            edificioATrabajar = aReparar;
        }else
        if(edificioATrabajar != aReparar){
            edificioATrabajar.suspender(esteAldeano);
        }
        edificioATrabajar = aReparar;
        return this;
    }

}

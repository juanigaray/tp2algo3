package com.company.modelo.edificios.estados;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioEnReparacionException;
import com.company.excepciones.Edificio.EdificioOcupadoException;
import com.company.excepciones.Edificio.EdificioReparadoException;
import com.company.excepciones.EdificioDestruidoExcepcion;
import com.company.excepciones.MapaLlenoException;
import com.company.modelo.Posicion;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

public abstract class EstadoEdificio {

    protected static Integer VIDA_MAXIMA ;
    protected static Integer MONTO_REPARACION;
    protected  static Integer vidaActual;
    protected static Aldeano trabajadorActual = null;

    public EstadoEdificio(Integer vida_maxima,Integer vida_actual, Integer monto) {

        this.VIDA_MAXIMA = vida_maxima;
        this.vidaActual = vida_actual;
        this.MONTO_REPARACION = monto;

    }

    public abstract EstadoEdificio crear(Unidad unidad, Posicion posicion)
            throws EdificioEnConstruccionException, EdificioEnReparacionException,
            CasilleroLlenoException, CasilleroNoExistenteException,
            MapaLlenoException;

    public EstadoEdificio recibirDanio(Integer montoDeDanio) throws EdificioEnConstruccionException, EdificioDestruidoExcepcion {

        if(montoDeDanio < 0){
            throw new RuntimeException("El daño recibido fue negativo toddo mal.");
        }

        this.vidaActual -= montoDeDanio;
        if(vidaActual <= 0){
           throw new EdificioDestruidoExcepcion("Este edificio esta destruido");
        }
        return this;
    }

    public abstract EstadoEdificio reparar(Aldeano reparador, Integer montoDeReparacion)
            throws Exception, EdificioEnConstruccionException;

    public abstract EstadoEdificio construir(Aldeano quienLoConstruye)
            throws Exception;

    public abstract EstadoEdificio suspender() throws Exception, EdificioEnConstruccionException;

    public Integer getVidaActual() throws Exception{

            return vidaActual;

    }

    public abstract EstadoEdificio ejecutarAccion() throws Exception;

}
/*estado puede estar reparando, construyendo o haciendo nada si esta reparando entonces hasta que no termine
;a vda del edifico  no termina si esta construyendo dura la cantidad de turnos
 */

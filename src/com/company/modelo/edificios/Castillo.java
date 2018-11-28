package com.company.modelo.edificios;

import com.company.excepciones.*;
import com.company.modelo.Ataque;
import com.company.modelo.Jugador;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.estados.EstadoEdificioInactivo;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.Unidad;

public class Castillo extends Edificio {

    private Integer rangoAtaque;
    private Integer danioAPosicionable;

    public Castillo(Jugador jugador) {
        super(jugador);
        COSTO = 0;
        MONTO_DE_REPARACION = 15;
        BLOQUES_DE_ANCHO = 8;
        BLOQUES_DE_ALTO = 8;
        VIDA_MAXIMA = 1000;
        this.rangoAtaque = 7; // porque es a partir del centro del castillo
        this.danioAPosicionable = 20;
        this.estado = new EstadoEdificioInactivo(VIDA_MAXIMA, VIDA_MAXIMA, MONTO_DE_REPARACION);
    }

    @Override
    public void crear(Unidad unidad) throws CasilleroNoExistenteException,
            CasilleroLlenoException, UnidadErroneaException, MapaLlenoException {

        //esto no funciona
        //if( !(estado instanceof EstadoEdificioCreando) | !(estado instanceof EstadoEdificioInactivo)  ) throw new EdificioNoDisponibleException("El edificio no esta disponible");

        if (!(unidad instanceof ArmaAsedio)) {
            throw new UnidadErroneaException("Imposible crear ese tipo de unidad");
        }

        posiciones.get(1).colocarEnCasilleroLibreMasCercano(unidad);
        jugador.agregarAPoblacion(unidad);
    }

    public void surgir(Integer posicionHorizontal, Integer posicionVertical) throws CasilleroNoExistenteException, CasilleroLlenoException {
        this.ubicar(posicionHorizontal, posicionVertical);
        jugador.agregarAEdificios(this);
    }

    public void atacarA(Posicionable enemigo) throws EnemigoInvalidoException {
        this.atacar(enemigo, this.danioAPosicionable);
    }

    private void atacar(Posicionable unEnemigo, Integer unDanio) throws EnemigoInvalidoException {
        Ataque ataque = new Ataque(rangoAtaque, danioAPosicionable, danioAPosicionable, jugador, posiciones.get(36)); //la posicion 36 es donde esta el centro del castillo
        ataque.atacar(unEnemigo, unDanio);
    }


}

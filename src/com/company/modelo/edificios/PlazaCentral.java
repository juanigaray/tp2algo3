package com.company.modelo.edificios;

import com.company.modelo.Jugador;

public class PlazaCentral extends Edificio {

	private static final Integer VIDA_MAXIMA = 450;

	public PlazaCentral(Jugador jugador) {
		super(jugador);
		this.estado = new EstadoEdificio(VIDA_MAXIMA,100,25,4 ,3);
	}

}
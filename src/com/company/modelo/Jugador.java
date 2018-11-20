package com.company.modelo;

import java.util.ArrayList;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.modelo.edificios.Castillo;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

public class Jugador {
	
	ArrayList <Unidad> poblacion;
	ArrayList <Edificio> edificios;
	public int oro;

	public Jugador(Mapa mapa) throws CasilleroLlenoException {
		this.oro = 100;
	}
	
	public void mover(int x, int y, Unidad unidad) throws CasilleroNoExistenteException {
		try {
			unidad.moverA(x,y);
		} catch (CasilleroLlenoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarAPoblacion(Unidad unidad) {
		poblacion.add(unidad);
	}
	
	public void eliminarDePoblacion(Unidad unidad) {
		//if unidad no existe lanzar excepcion, nose si la coleccion lo tiene automatizado
		poblacion.remove(unidad);
	}

	public boolean tieneOro(int i) {
		return this.oro >= i;
	}

	public void sumarOro(int produccionOro) {
		this.oro += produccionOro;
		
	}
	
	public void actualizar() {
		
	}

	public void crearEntidadesIniciales(){
		//Le pasa el mapa a los edificios para permitirles ubicar unidades con su posicion

		Castillo castillo = new Castillo(this);
		Aldeano constructor =  new Aldeano(this);

		try {
			castillo.construirEn(constructor,1, 1);
		} catch (CasilleroLlenoException e) {
			e.printStackTrace();
		}
		edificios.add(castillo);

		PlazaCentral plaza = new PlazaCentral(this);

		try {
			plaza.construirEn(constructor,5, 0);
		} catch (CasilleroLlenoException e) {
			e.printStackTrace();
		}
		edificios.add(plaza);

		poblacion.add(new Aldeano(this));
		poblacion.add(new Aldeano(this));
		poblacion.add(new Aldeano(this));

		poblacion.get(0).naciEn(5,3);
		poblacion.get(1).naciEn(6,3);
		poblacion.get(2).naciEn(7,0);
	}

	public void eliminarDeConstrucciones(Edificio edificio) {
		edificios.remove(edificio);
	}
}

package Racer;

import java.util.ArrayList;

public class Question {
	
	private int id, idOpcionCorrecta, dificultad;
	private String enunciado;
	private ArrayList<Option> opciones = new ArrayList<Option>(N); // N CANTIDAD DE OPCIONES
	
	
	public Question(int id, int idOpcionCorrecta, int dificultad, String enunciado, ArrayList<Option> opciones) {
		this.id = id;
		this.idOpcionCorrecta = idOpcionCorrecta;
		this.dificultad = dificultad;
		this.enunciado = enunciado;
		this.opciones = opciones;
	}


	public int getId() {
		return id;
	}


	public int getIdOpcionCorrecta() {
		return idOpcionCorrecta;
	}


	public int getDificultad() {
		return dificultad;
	}


	public String getEnunciado() {
		return enunciado;
	}


	public ArrayList<Option> getOpciones() {
		return opciones;
	}
	/*
	public void muestraPregunta() { ESTA CLASE SE NECESITA EL XML PARA VER BIEN COMO ARMARLO
		
		System.out.println(enunciado); // MUESTRA LA PREGUNTA
		
		int i = 0;
		while (i < opciones.size()) { // MUESTRA LAS OPCIONES CON 1 2 3 4 ETC.
			
			System.out.println("\t"+(i+1)+". "+opciones.get(i));
			i++;
		}
	} 
	*/
	
	public boolean respuestaCorrecta(Option id) {
		
		int opcionElegida = Integer.parseInt(id.toString());
		
		if (opcionElegida = idOpcionCorrecta) {
			return opcionElegida.isCorrecta();
		}
	}
	
	

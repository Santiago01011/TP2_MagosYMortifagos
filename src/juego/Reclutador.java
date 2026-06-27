package juego;

import hechizos.ForjadorHechizos;
import personajes.Auror;
import personajes.Comandante;
import personajes.Estudiante;
import personajes.Mago;
import personajes.Mortifago;
import personajes.Personaje;
import personajes.Profesor;
import personajes.Seguidor;

public class Reclutador {

	public static Personaje crearMago() {
		String[] nombres = {"Harry", "Hermione", "Dumbledore", "McGonagall", "Luna"};
		String nombre = nombres[(int) (Math.random() * nombres.length)];
		int nivelMagia = 30 + (int) (Math.random() * 40); // 30-69
		int vida = 80 + (int) (Math.random() * 40); // 80-119

		int tipo = (int) (Math.random() * 3);
		Mago mago;
		switch (tipo) {
			case 0:
				mago = new Auror(nombre, nivelMagia, vida);
				break;
			case 1:
				mago = new Estudiante(nombre, nivelMagia, vida);
				break;
			default:
				mago = new Profesor(nombre, nivelMagia, vida);
				break;
		}

		mago.agregarHechizo(ForjadorHechizos.crear("expelliarmus"));
		mago.agregarHechizo(ForjadorHechizos.crear("protego"));
		mago.agregarHechizo(ForjadorHechizos.crear("expecto patronum"));
		return mago;
	}

	public static Personaje crearMortifago() {
		String[] nombres = {"Voldemort", "Bellatrix", "Lucius", "Wormtail", "Draco"};
		String nombre = nombres[(int) (Math.random() * nombres.length)];
		int nivelMagia = 30 + (int) (Math.random() * 40); // 30-69
		int vida = 80 + (int) (Math.random() * 40); // 80-119

		int tipo = (int) (Math.random() * 2);
		Mortifago mortifago;
		if (tipo == 0) {
			mortifago = new Seguidor(nombre, nivelMagia, vida);
		} else {
			mortifago = new Comandante(nombre, nivelMagia, vida);
		}

		mortifago.agregarHechizo(ForjadorHechizos.crear("avada kedavra"));
		mortifago.agregarHechizo(ForjadorHechizos.crear("crucio"));
		mortifago.agregarHechizo(ForjadorHechizos.crear("protego"));
		return mortifago;
	}
}

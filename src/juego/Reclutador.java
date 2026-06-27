package juego;

import hechizos.ForjadorHechizos;
import personajes.Mago;
import personajes.Mortifago;
import personajes.Personaje;

public class Reclutador {

	public static Personaje crearMago() {
		String[] nombres = {"Harry", "Hermione", "Dumbledore", "McGonagall", "Luna"};
		String nombre = nombres[(int) (Math.random() * nombres.length)];
		int nivelMagia = 30 + (int) (Math.random() * 40); // 30-69
		int vida = 80 + (int) (Math.random() * 40); // 80-119

		Mago mago = new Mago(nombre, nivelMagia, vida);
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

		Mortifago mortifago = new Mortifago(nombre, nivelMagia, vida);
		mortifago.agregarHechizo(ForjadorHechizos.crear("avada kedavra"));
		mortifago.agregarHechizo(ForjadorHechizos.crear("crucio"));
		mortifago.agregarHechizo(ForjadorHechizos.crear("protego"));
		return mortifago;
	}
}

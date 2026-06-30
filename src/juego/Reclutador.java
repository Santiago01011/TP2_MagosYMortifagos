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
		int vida = 200 + (int) (Math.random() * 100); // 200-299

		int tipo = (int) (Math.random() * 3);
		Mago mago;
                mago = switch (tipo) {
                case 0 -> new Auror(nombre, nivelMagia, vida);
                case 1 -> new Estudiante(nombre, nivelMagia, vida);
                default -> new Profesor(nombre, nivelMagia, vida);
            };

		mago.agregarHechizo(ForjadorHechizos.crear("expelliarmus"));
		mago.agregarHechizo(ForjadorHechizos.crear("protego"));
		mago.agregarHechizo(ForjadorHechizos.crear("expecto patronum"));
		return mago;
	}

	public static Personaje crearMortifago() {
		String[] nombres = {"Voldemort", "Bellatrix", "Lucius", "Wormtail", "Draco"};
		String nombre = nombres[(int) (Math.random() * nombres.length)];
		int nivelMagia = 30 + (int) (Math.random() * 40);
		int vida = 200 + (int) (Math.random() * 100);

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

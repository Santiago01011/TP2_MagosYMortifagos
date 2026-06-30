package efectos;

import personajes.Personaje;
import hechizos.Hechizo;

public interface EfectoEstado {

	default void alIniciarTurno(Personaje objetivo) {
	}

	default int modificarDañoRecibido(int daño) {
		return daño;
	}

	default int modificarAtaque(int daño, Hechizo hechizo, Personaje lanzador) {
		return daño;
	}

	default boolean permiteActuar() {
		return true;
	}

	boolean haExpirado();

	String getNombre();
}

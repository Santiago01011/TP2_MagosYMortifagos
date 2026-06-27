package efectos;

import personajes.Personaje;

public interface EfectoEstado {

	default void alIniciarTurno(Personaje objetivo) {
	}

	default int modificarDañoRecibido(int daño) {
		return daño;
	}

	default boolean permiteActuar() {
		return true;
	}

	boolean haExpirado();

	String getNombre();
}

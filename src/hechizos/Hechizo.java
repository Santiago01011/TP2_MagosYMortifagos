package hechizos;

import personajes.Personaje;

public interface Hechizo {
	String nombre();
	void ejecutar(Personaje lanzador, Personaje objetivo);
	boolean esOscuridad();
}

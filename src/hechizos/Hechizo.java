package hechizos;

import personajes.Personaje;

public interface Hechizo {
	String nombre();
	String ejecutar(Personaje lanzador, Personaje objetivo);
	boolean esOscuridad();

	default Personaje seleccionarObjetivo(Personaje lanzador, java.util.List<Personaje> aliados, java.util.List<Personaje> enemigos) {
		java.util.List<Personaje> vivos = enemigos.stream().filter(Personaje::estaVivo).toList();
		if (vivos.isEmpty()) {
			return null;
		}
		return vivos.get((int) (Math.random() * vivos.size()));
	}
}


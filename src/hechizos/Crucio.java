package hechizos;

import efectos.Sangrado;
import personajes.Personaje;

public class Crucio extends HechizoAtaque {
	public Crucio() {
		super("Crucio", 15);
	}

	@Override
	protected void aplicarEfectosAdicionales(Personaje objetivo) {
		objetivo.aplicarEfecto(new Sangrado(10, 2));
	}

	@Override
	protected String obtenerLog(Personaje lanzador, Personaje objetivo, int daño) {
		return lanzador.getNombre() + " lanza " + this + " a " + objetivo.getNombre() + " → " + daño + " de daño y deja sangrado";
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

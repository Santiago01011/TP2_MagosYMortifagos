package hechizos;

import efectos.Sangrado;
import personajes.Personaje;

public class Crucio extends HechizoAtaque {
	public Crucio() {
		super("Crucio", 30);
	}

	@Override
	protected void aplicarEfectosAdicionales(Personaje objetivo) {
		objetivo.aplicarEfecto(new Sangrado(10, 2));
	}

	@Override
	protected void imprimirLog(Personaje lanzador, Personaje objetivo, int daño) {
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño y deja sangrado");
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

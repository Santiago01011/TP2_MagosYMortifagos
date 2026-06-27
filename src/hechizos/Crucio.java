package hechizos;

import efectos.Sangrado;
import personajes.Personaje;

public class Crucio extends HechizoAtaque {
	public Crucio() {
		super("Crucio", 30);
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int daño = calcularDaño();
		daño = lanzador.aplicarBonusAtaque(daño, this);
		daño = lanzador.aplicarBonusObjetosAtaque(daño, this);
		objetivo.recibirDaño(daño);
		objetivo.aplicarEfecto(new Sangrado(10, 2));
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño y deja sangrado");
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

package hechizos;

import personajes.Personaje;

public class Crucio extends HechizoAtaque {
	public Crucio() {
		super("Crucio", 30);
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int daño = calcularDaño();
		daño = lanzador.aplicarBonusAtaque(daño, this);
		objetivo.recibirDaño(daño);
		objetivo.aplicarEfectoEstado("dolor", 2); // 2 rondas de efecto
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño + dolor por 2 rondas");
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

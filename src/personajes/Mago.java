package personajes;

import hechizos.Hechizo;

public class Mago extends Personaje {

	public Mago(String nombre, int nivelMagia, int puntosDeVida) {
		super(nombre, nivelMagia, puntosDeVida);
	}

	@Override
	public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
		// Magos son mejores con hechizos normales
		return hechizo.esOscuridad() ? dañoBase : dañoBase + (nivelMagia / 5);
	}

	@Override
	public int aplicarBonusCuracion(int curacionBase) {
		// Magos curan mejor
		return curacionBase + (nivelMagia / 3);
	}
}

package personajes;

import hechizos.Hechizo;

public class Mortifago extends Personaje {

	public Mortifago(String nombre, int nivelMagia, int puntosDeVida) {
		super(nombre, nivelMagia, puntosDeVida);
	}

	@Override
	public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
		return hechizo.esOscuridad() ? dañoBase + (nivelMagia / 2) : dañoBase;
	}

	@Override
	public int aplicarBonusCuracion(int curacionBase) {
		return curacionBase / 2;
	}
}

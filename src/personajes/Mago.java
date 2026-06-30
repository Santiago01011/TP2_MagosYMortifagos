package personajes;

import hechizos.Hechizo;

public class Mago extends Personaje {

	public Mago(String nombre, int nivelMagia, int puntosDeVida) {
		super(nombre, nivelMagia, puntosDeVida);
	}

	/**
	 * Los magos tienen un bonus de ataque que depende de su nivel de magia y del tipo de hechizo que estén usando. 
	 * Si el hechizo es de oscuridad, no reciben ningún bonus adicional. 
	 * Si el hechizo no es de oscuridad, reciben un bonus adicional basado en su nivel de magia.
	 * @param dañoBase El daño base del hechizo.
	 * @param hechizo El hechizo que se está utilizando.
	 * @return El daño total después de aplicar el bonus de ataque.
	 */
	@Override
	public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
		return hechizo.esOscuridad() ? dañoBase : dañoBase + (nivelMagia / 5);
	}

	/**
	 * Los magos tienen un bonus de curación que depende de su nivel de magia.
	 * @param curacionBase La cantidad base de curación.
	 * @return La cantidad total de curación después de aplicar el bonus.
	 */
	@Override
	public int aplicarBonusCuracion(int curacionBase) {
		return curacionBase + (nivelMagia / 3);
	}
}

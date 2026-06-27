package personajes;

import hechizos.Hechizo;

public class Comandante extends Mortifago {

	private boolean furiaActiva;

	public Comandante(String nombre, int nivelMagia, int puntosDeVida) {
		super(nombre, nivelMagia, puntosDeVida);
		this.furiaActiva = false;
	}

	@Override
	public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
		int base = super.aplicarBonusAtaque(dañoBase, hechizo);
		int conTipo = base + (hechizo.esOscuridad() ? (nivelMagia / 2) : (nivelMagia / 6));
		if (furiaActiva) {
			furiaActiva = false;
			System.out.println("  " + nombre + " descarga su furia");
			return conTipo + nivelMagia;
		}
		return conTipo;
	}

	@Override
	public int aplicarBonusCuracion(int curacionBase) {
		return Math.max(0, super.aplicarBonusCuracion(curacionBase));
	}

	@Override
	public void alCaerAliado(Personaje caido) {
		if (estaVivo()) {
			furiaActiva = true;
			System.out.println("¡" + nombre + " entra en furia por la caída de " + caido.getNombre() + "!");
		}
	}

	public boolean tieneFuriaActiva() {
		return furiaActiva;
	}
}

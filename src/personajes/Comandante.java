package personajes;

import efectos.EfectoEstado;
import efectos.Furia;
import hechizos.Hechizo;

public class Comandante extends Mortifago {

	public Comandante(String nombre, int nivelMagia, int puntosDeVida) {
		super(nombre, nivelMagia, puntosDeVida);
	}

	@Override
	public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
		int base = super.aplicarBonusAtaque(dañoBase, hechizo);
		int dañoConBonus = base + (hechizo.esOscuridad() ? (nivelMagia / 2) : (nivelMagia / 6));
		for (EfectoEstado efecto : efectos) {
			if (efecto instanceof Furia) {
				dañoConBonus = efecto.modificarAtaque(dañoConBonus, hechizo, this);
			}
		}
		return dañoConBonus;
	}

	@Override
	public void alCaerAliado() {
		if (estaVivo()) {
			this.aplicarEfecto(new Furia(nivelMagia));
		}
	}

	@Override
	public String reaccionAlCaerAliado(Personaje caido) {
		return estaVivo() ? "¡" + nombre + " entra en furia por la caída de " + caido.getNombre() + "!" : "";
	}

	public boolean tieneFuriaActiva() {
		for (EfectoEstado efecto : efectos) {
			if (efecto.getNombre().equalsIgnoreCase("Furia") && !efecto.haExpirado()) {
				return true;
			}
		}
		return false;
	}
}

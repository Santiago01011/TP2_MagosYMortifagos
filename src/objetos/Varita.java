package objetos;

import hechizos.Hechizo;

public class Varita implements ObjetoMagico {
	private int potencia;

	public Varita(int potencia) {
		this.potencia = potencia;
	}

	@Override
	public int modificarAtaque(int daño, Hechizo hechizo) {
		return daño + potencia;
	}

	@Override
	public String getNombre() {
		return "Varita";
	}
}

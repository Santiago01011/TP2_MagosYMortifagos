package efectos;

import hechizos.Hechizo;
import personajes.Personaje;

public class Furia implements EfectoEstado {
	private int potencia;
	private boolean usada;

	public Furia(int potencia) {
		this.potencia = potencia;
		this.usada = false;
	}

	@Override
	public int modificarAtaque(int daño, Hechizo hechizo, Personaje lanzador) {
		if (!usada) {
			usada = true;
			System.out.println("  " + lanzador.getNombre() + " descarga su furia");
			return daño + potencia;
		}
		return daño;
	}

	@Override
	public boolean haExpirado() {
		return usada;
	}

	@Override
	public String getNombre() {
		return "Furia";
	}
}

package hechizos;

import personajes.Personaje;

public class AvadaKedavra extends HechizoAtaque {
	public AvadaKedavra() {
		super("Avada Kedavra", 100);
	}

	@Override
	protected void aplicarDaño(Personaje objetivo, int daño) {
		objetivo.recibirDañoDirecto(daño);
	}

	@Override
	protected void imprimirLog(Personaje lanzador, Personaje objetivo, int daño) {
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño (ignora defensa)");
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

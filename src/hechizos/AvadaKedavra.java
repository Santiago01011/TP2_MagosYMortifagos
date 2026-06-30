package hechizos;

import personajes.Personaje;

public class AvadaKedavra extends HechizoAtaque {
	public AvadaKedavra() {
		super("Avada Kedavra", 40);
	}

	@Override
	protected void aplicarDaño(Personaje objetivo, int daño) {
		objetivo.recibirDañoDirecto(daño);
	}

	@Override
	protected String obtenerLog(Personaje lanzador, Personaje objetivo, int daño) {
		return lanzador.getNombre() + " lanza " + this + " a " + objetivo.getNombre() + " → " + daño + " de daño (ignora defensa)";
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

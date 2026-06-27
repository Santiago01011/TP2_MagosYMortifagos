package hechizos;

import personajes.Personaje;

public class AvadaKedavra extends HechizoAtaque {
	public AvadaKedavra() {
		super("Avada Kedavra", 100);
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int daño = calcularDaño();
		daño = lanzador.aplicarBonusAtaque(daño, this);
		daño = lanzador.aplicarBonusObjetosAtaque(daño, this);
		objetivo.recibirDañoDirecto(daño);
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño (ignora defensa)");
	}

	@Override
	public boolean esOscuridad() {
		return true;
	}
}

package hechizos;

import personajes.Personaje;

public abstract class HechizoAtaque implements Hechizo {
	protected String nombre;
	protected int baseDaño;

	public HechizoAtaque(String nombre, int baseDaño) {
		this.nombre = nombre;
		this.baseDaño = baseDaño;
	}

	@Override
	public String nombre() {
		return nombre;
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int daño = calcularDaño();
		daño = lanzador.aplicarBonusAtaque(daño, this);
		objetivo.recibirDaño(daño);
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño");
	}

	protected int calcularDaño() {
		return baseDaño;
	}

	@Override
	public boolean esOscuridad() {
		return false;
	}
}

package hechizos;

import personajes.Personaje;

public abstract class HechizoDefensa implements Hechizo {
	protected String nombre;
	protected int duracionEnRondas;

	public HechizoDefensa(String nombre, int duracionEnRondas) {
		this.nombre = nombre;
		this.duracionEnRondas = duracionEnRondas;
	}

	@Override
	public String nombre() {
		return nombre;
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.aplicarProteccion(duracionEnRondas);
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " → protección por " + duracionEnRondas + " ronda(s)");
	}

	@Override
	public boolean esOscuridad() {
		return false;
	}
}

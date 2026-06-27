package hechizos;

import efectos.Escudo;
import personajes.Personaje;

public abstract class HechizoDefensa implements Hechizo {
	protected String nombre;
	protected int duracionEnRondas;
	protected int reduccion;

	public HechizoDefensa(String nombre, int duracionEnRondas, int reduccion) {
		this.nombre = nombre;
		this.duracionEnRondas = duracionEnRondas;
		this.reduccion = reduccion;
	}

	@Override
	public String nombre() {
		return nombre;
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.aplicarEfecto(new Escudo(reduccion, duracionEnRondas));
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " → escudo del " + reduccion + "% por " + duracionEnRondas + " ronda(s)");
	}

	@Override
	public boolean esOscuridad() {
		return false;
	}
}

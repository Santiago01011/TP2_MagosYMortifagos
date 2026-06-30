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
	public String ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.aplicarEfecto(new Escudo(reduccion, duracionEnRondas));
		return lanzador.getNombre() + " lanza " + this + " a " + objetivo.getNombre() + " → escudo del " + reduccion + "% por " + duracionEnRondas + " ronda(s)";
	}

	@Override
	public Personaje seleccionarObjetivo(Personaje lanzador, java.util.List<Personaje> aliados, java.util.List<Personaje> enemigos) {
		return lanzador;
	}

	@Override
	public boolean esOscuridad() {
		return false;
	}

	@Override
	public String toString() {
		return nombre;
	}
}

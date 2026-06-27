package hechizos;

import personajes.Personaje;

public abstract class HechizoCuracion implements Hechizo {
	protected String nombre;
	protected int baseCuracion;

	public HechizoCuracion(String nombre, int baseCuracion) {
		this.nombre = nombre;
		this.baseCuracion = baseCuracion;
	}

	@Override
	public String nombre() {
		return nombre;
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacion = baseCuracion;
		curacion = lanzador.aplicarBonusCuracion(curacion);
		objetivo.curar(curacion);
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " → +" + curacion + " de vida");
	}

	@Override
	public boolean esOscuridad() {
		return false;
	}
}

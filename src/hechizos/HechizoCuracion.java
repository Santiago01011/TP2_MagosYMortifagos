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
	public String ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacion = baseCuracion;
		curacion = lanzador.aplicarBonusCuracion(curacion);
		curacion = lanzador.aplicarBonusObjetosCuracion(curacion);
		objetivo.curar(curacion);
		return lanzador.getNombre() + " lanza " + this + " a " + objetivo.getNombre() + " → +" + curacion + " de vida";
	}

	@Override
	public Personaje seleccionarObjetivo(Personaje lanzador, java.util.List<Personaje> aliados, java.util.List<Personaje> enemigos) {
		java.util.List<Personaje> vivos = aliados.stream().filter(Personaje::estaVivo).toList();
		if (vivos.isEmpty()) {
			return null;
		}
		Personaje masHerido = vivos.get(0);
		for (Personaje p : vivos) {
			if (p.getPuntosDeVida() < masHerido.getPuntosDeVida()) {
				masHerido = p;
			}
		}
		return masHerido;
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

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
		daño = lanzador.aplicarBonusObjetosAtaque(daño, this);
		aplicarDaño(objetivo, daño);
		aplicarEfectosAdicionales(objetivo);
		imprimirLog(lanzador, objetivo, daño);
	}

	protected int calcularDaño() {
		return baseDaño;
	}

	protected void aplicarDaño(Personaje objetivo, int daño) {
		objetivo.recibirDaño(daño);
	}

	protected void aplicarEfectosAdicionales(Personaje objetivo) {}

	protected void imprimirLog(Personaje lanzador, Personaje objetivo, int daño) {
		System.out.println(lanzador.getNombre() + " lanza " + nombre + " a " + objetivo.getNombre() + " → " + daño + " de daño");
	}

	@Override
	public Personaje seleccionarObjetivo(Personaje lanzador, java.util.List<Personaje> aliados, java.util.List<Personaje> enemigos) {
		java.util.List<Personaje> vivos = enemigos.stream().filter(Personaje::estaVivo).toList();
		if (vivos.isEmpty()) {
			return null;
		}
		return vivos.get((int) (Math.random() * vivos.size()));
	}

	@Override
	public boolean esOscuridad() {
		return false;
	}
}


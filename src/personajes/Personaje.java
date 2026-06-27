package personajes;

import java.util.ArrayList;
import java.util.List;

import hechizos.Hechizo;

public abstract class Personaje {
	protected String nombre;
	protected int nivelMagia;
	protected int puntosDeVida;
	protected int vidaMax;
	protected List<Hechizo> hechizos;
	protected int rondasProtegido;
	protected String efectoEstado;
	protected int rondasEfecto;

	public Personaje(String nombre, int nivelMagia, int puntosDeVida) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosDeVida = puntosDeVida;
		this.vidaMax = puntosDeVida;
		this.hechizos = new ArrayList<>();
		this.rondasProtegido = 0;
		this.efectoEstado = null;
		this.rondasEfecto = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntosDeVida() {
		return puntosDeVida;
	}

	public boolean estaVivo() {
		return puntosDeVida > 0;
	}

	public void recibirDaño(int daño) {
		if (rondasProtegido > 0) {
			daño = daño / 2; // reduce daño si está protegido
			rondasProtegido--;
			System.out.println("  " + nombre + " está protegido, daño reducido");
		}
		this.puntosDeVida = Math.max(0, this.puntosDeVida - daño);
	}

	public void curar(int cantidad) {
		this.puntosDeVida = Math.min(vidaMax, this.puntosDeVida + cantidad);
	}

	public void aplicarProteccion(int rondas) {
		this.rondasProtegido += rondas;
	}

	public void aplicarEfectoEstado(String efecto, int rondas) {
		this.efectoEstado = efecto;
		this.rondasEfecto = rondas;
	}

	public void agregarHechizo(Hechizo hechizo) {
		hechizos.add(hechizo);
	}

	public List<Hechizo> getHechizos() {
		return hechizos;
	}

	// Polimorfismo — cada subclase define su propio bonus
	public abstract int aplicarBonusAtaque(int dañoBase, Hechizo hechizo);
	public abstract int aplicarBonusCuracion(int curacionBase);
}

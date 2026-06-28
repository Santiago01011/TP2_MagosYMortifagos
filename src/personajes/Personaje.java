package personajes;

import java.util.ArrayList;
import java.util.List;

import efectos.EfectoEstado;
import hechizos.Hechizo;
import objetos.ObjetoMagico;

public abstract class Personaje {
	protected String nombre;
	protected int nivelMagia;
	protected int puntosDeVida;
	protected int vidaMax;
	protected List<Hechizo> hechizos;
	protected List<EfectoEstado> efectos;
	protected List<ObjetoMagico> objetos;

	public Personaje(String nombre, int nivelMagia, int puntosDeVida) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosDeVida = puntosDeVida;
		this.vidaMax = puntosDeVida;
		this.hechizos = new ArrayList<>();
		this.efectos = new ArrayList<>();
		this.objetos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivelMagia() {
		return nivelMagia;
	}

	public int getPuntosDeVida() {
		return puntosDeVida;
	}

	public boolean estaVivo() {
		return puntosDeVida > 0;
	}

	public void recibirDaño(int daño) {
		int dañoFinal = daño;
		for (EfectoEstado efecto : efectos) {
			dañoFinal = efecto.modificarDañoRecibido(dañoFinal);
		}
		for (ObjetoMagico objeto : objetos) {
			dañoFinal = objeto.alRecibirDaño(dañoFinal);
		}
		if (dañoFinal < 0) {
			dañoFinal = 0;
		}
		this.puntosDeVida = Math.max(0, this.puntosDeVida - dañoFinal);
	}

	public void recibirDañoDirecto(int daño) {
		this.puntosDeVida = Math.max(0, this.puntosDeVida - daño);
	}

	public void curar(int cantidad) {
		if (!estaVivo()) {
			return;
		}
		this.puntosDeVida = Math.min(vidaMax, this.puntosDeVida + cantidad);
	}

	public void aplicarEfecto(EfectoEstado efecto) {
		if (!estaVivo()) {
			return;
		}
		efectos.add(efecto);
	}

	public void iniciarTurno() {
		for (EfectoEstado efecto : efectos) {
			efecto.alIniciarTurno(this);
		}
		List<EfectoEstado> activos = new ArrayList<>();
		for (EfectoEstado efecto : efectos) {
			if (!efecto.haExpirado()) {
				activos.add(efecto);
			}
		}
		this.efectos = activos;
	}

	public boolean puedeActuar() {
		for (EfectoEstado efecto : efectos) {
			if (!efecto.permiteActuar()) {
				return false;
			}
		}
		return true;
	}

	public void equipar(ObjetoMagico objeto) {
		objetos.add(objeto);
	}

	public void quitar(ObjetoMagico objeto) {
		objetos.remove(objeto);
	}

	public int aplicarBonusObjetosAtaque(int daño, Hechizo hechizo) {
		int resultado = daño;
		for (ObjetoMagico objeto : objetos) {
			resultado = objeto.modificarAtaque(resultado, hechizo);
		}
		return resultado;
	}

	public int aplicarBonusObjetosCuracion(int cura) {
		int resultado = cura;
		for (ObjetoMagico objeto : objetos) {
			resultado = objeto.modificarCuracion(resultado);
		}
		return resultado;
	}

	public int descansar(int magiaBase) {
		int resultado = magiaBase;
		for (ObjetoMagico objeto : objetos) {
			resultado = objeto.modificarDescanso(resultado);
		}
		this.nivelMagia += resultado;
		return resultado;
	}

	public void alCaerAliado(Personaje caido) {
	}

	public void agregarHechizo(Hechizo hechizo) {
		hechizos.add(hechizo);
	}

	public List<Hechizo> getHechizos() {
		return hechizos;
	}

	public abstract int aplicarBonusAtaque(int dañoBase, Hechizo hechizo);

	public abstract int aplicarBonusCuracion(int curacionBase);
}

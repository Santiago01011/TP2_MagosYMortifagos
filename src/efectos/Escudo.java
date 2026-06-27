package efectos;

import personajes.Personaje;

public class Escudo implements EfectoEstado {
	private int reduccion;
	private int turnosRestantes;

	public Escudo(int reduccion, int turnos) {
		this.reduccion = reduccion;
		this.turnosRestantes = turnos;
	}

	@Override
	public void alIniciarTurno(Personaje objetivo) {
		if (turnosRestantes > 0) {
			turnosRestantes--;
		}
	}

	@Override
	public int modificarDañoRecibido(int daño) {
		if (turnosRestantes > 0) {
			return daño - (daño * reduccion / 100);
		}
		return daño;
	}

	@Override
	public boolean haExpirado() {
		return turnosRestantes <= 0;
	}

	@Override
	public String getNombre() {
		return "Escudo";
	}
}

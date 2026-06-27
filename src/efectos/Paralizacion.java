package efectos;

import personajes.Personaje;

public class Paralizacion implements EfectoEstado {
	private int turnosRestantes;

	public Paralizacion(int turnos) {
		this.turnosRestantes = turnos;
	}

	@Override
	public void alIniciarTurno(Personaje objetivo) {
		if (turnosRestantes > 0) {
			turnosRestantes--;
			System.out.println("  " + objetivo.getNombre() + " está paralizado");
		}
	}

	@Override
	public boolean permiteActuar() {
		return turnosRestantes <= 0;
	}

	@Override
	public boolean haExpirado() {
		return turnosRestantes <= 0;
	}

	@Override
	public String getNombre() {
		return "Paralizacion";
	}
}

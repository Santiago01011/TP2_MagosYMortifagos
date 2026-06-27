package efectos;

import personajes.Personaje;

public class Sangrado implements EfectoEstado {
	private int dañoPorTurno;
	private int turnosRestantes;

	public Sangrado(int dañoPorTurno, int turnos) {
		this.dañoPorTurno = dañoPorTurno;
		this.turnosRestantes = turnos;
	}

	@Override
	public void alIniciarTurno(Personaje objetivo) {
		if (turnosRestantes > 0) {
			objetivo.recibirDañoDirecto(dañoPorTurno);
			turnosRestantes--;
			System.out.println("  " + objetivo.getNombre() + " sufre " + dañoPorTurno + " de sangrado");
		}
	}

	@Override
	public boolean haExpirado() {
		return turnosRestantes <= 0;
	}

	@Override
	public String getNombre() {
		return "Sangrado";
	}
}

package efectos;

import personajes.Personaje;

public class Regeneracion implements EfectoEstado {
	private int curaPorTurno;
	private int turnosRestantes;

	public Regeneracion(int curaPorTurno, int turnos) {
		this.curaPorTurno = curaPorTurno;
		this.turnosRestantes = turnos;
	}

	@Override
	public void alIniciarTurno(Personaje objetivo) {
		if (turnosRestantes > 0) {
			objetivo.curar(curaPorTurno);
			turnosRestantes--;
			System.out.println("  " + objetivo.getNombre() + " regenera " + curaPorTurno + " de vida");
		}
	}

	@Override
	public boolean haExpirado() {
		return turnosRestantes <= 0;
	}

	@Override
	public String getNombre() {
		return "Regeneracion";
	}
}

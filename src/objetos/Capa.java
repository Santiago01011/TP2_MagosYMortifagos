package objetos;

public class Capa implements ObjetoMagico {
	private boolean usada;

	public Capa() {
		this.usada = false;
	}

	@Override
	public int alRecibirDaño(int daño) {
		if (!usada) {
			usada = true;
			return 0;
		}
		return daño;
	}

	@Override
	public String getNombre() {
		return "Capa";
	}
}

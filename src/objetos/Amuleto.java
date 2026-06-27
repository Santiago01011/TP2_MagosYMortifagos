package objetos;

public class Amuleto implements ObjetoMagico {
	private int extraMagia;

	public Amuleto(int extraMagia) {
		this.extraMagia = extraMagia;
	}

	@Override
	public int modificarDescanso(int magia) {
		return magia + extraMagia;
	}

	@Override
	public String getNombre() {
		return "Amuleto";
	}
}

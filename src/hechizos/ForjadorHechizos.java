package hechizos;

public class ForjadorHechizos {

	public static Hechizo crear(String nombre) {
		return switch (nombre.toLowerCase()) {
			case "expelliarmus" -> new Expelliarmus();
			case "avada kedavra" -> new AvadaKedavra();
			case "crucio" -> new Crucio();
			case "protego" -> new Protego();
			case "protego maxima" -> new ProtegoMaxima();
			case "expecto patronum" -> new ExpectoPatronum();
			default -> throw new IllegalArgumentException("Hechizo desconocido: " + nombre);
		};
	}
}

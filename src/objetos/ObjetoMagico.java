package objetos;

import hechizos.Hechizo;

public interface ObjetoMagico {

	default int modificarAtaque(int daño, Hechizo hechizo) {
		return daño;
	}

	default int modificarCuracion(int cura) {
		return cura;
	}

	default int alRecibirDaño(int daño) {
		return daño;
	}

	default int modificarDescanso(int magia) {
		return magia;
	}

	String getNombre();
}

package juego;

import java.util.Random;

public class BatallaMagosVsMortifagos {

	public static void main(String[] args) {
		Batallon batallonMagos = new Batallon();
		Batallon batallonMortifagos = new Batallon();
		for (int i = 0; i < 4; i++) {
			batallonMagos.agregarPersonaje(Reclutador.crearMago());
			batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
		}
		Random rand = new Random();
		while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
			if (rand.nextBoolean()) {
				batallonMagos.atacar(batallonMortifagos);
				if (batallonMortifagos.tienePersonajesSaludables()) {
					batallonMortifagos.atacar(batallonMagos);
				}
			} else {
				batallonMortifagos.atacar(batallonMagos);
				if (batallonMagos.tienePersonajesSaludables()) {
					batallonMagos.atacar(batallonMortifagos);
				}
			}
			batallonMagos.mostrarEstado("Magos");
			batallonMortifagos.mostrarEstado("Mortífagos");
			System.out.println("----------------------------");
		}
		if (batallonMagos.tienePersonajesSaludables()) {
			System.out.println("¡Los magos han ganado la batalla!");
		} else {
			System.out.println("¡Los mortífagos han ganado la batalla!");
		}
	}
}

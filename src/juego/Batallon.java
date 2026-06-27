package juego;

import hechizos.Hechizo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import personajes.Personaje;

public class Batallon {
	private List<Personaje> personajes;
	private Map<Personaje, Set<Hechizo>> usadosEnRonda;

	public Batallon() {
		this.personajes = new ArrayList<>();
		this.usadosEnRonda = new HashMap<>();
	}

	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
	}

	public boolean tienePersonajesSaludables() {
		for (int i = 0; i < personajes.size(); i++) {
			if (personajes.get(i).estaVivo()) {
				return true;
			}
		}
		return false;
	}

	public void atacar(Batallon enemigo) {
		usadosEnRonda.clear();
		Random rand = new Random();

		for (int i = 0; i < personajes.size(); i++) {
			Personaje atacante = personajes.get(i);
			if (!atacante.estaVivo()) continue;
			if (!enemigo.tienePersonajesSaludables()) break;

			// Buscar hechizos disponibles (no usados esta ronda)
			List<Hechizo> disponibles = new ArrayList<>();
			Set<Hechizo> usados = usadosEnRonda.get(atacante);
			if (usados == null) {
				usados = new HashSet<>();
			}
			for (int j = 0; j < atacante.getHechizos().size(); j++) {
				Hechizo h = atacante.getHechizos().get(j);
				if (!usados.contains(h)) {
					disponibles.add(h);
				}
			}

			if (disponibles.isEmpty()) continue;

			Hechizo hechizo = disponibles.get(rand.nextInt(disponibles.size()));

			if (!usadosEnRonda.containsKey(atacante)) {
				usadosEnRonda.put(atacante, new HashSet<>());
			}
			usadosEnRonda.get(atacante).add(hechizo);

			Personaje objetivo = elegirObjetivo(enemigo);

			hechizo.ejecutar(atacante, objetivo);
		}
	}

	private Personaje elegirObjetivo(Batallon enemigo) {
		List<Personaje> vivos = new ArrayList<>();
		for (int i = 0; i < enemigo.personajes.size(); i++) {
			if (enemigo.personajes.get(i).estaVivo()) {
				vivos.add(enemigo.personajes.get(i));
			}
		}

		if (vivos.isEmpty()) return null;

		return vivos.get(new Random().nextInt(vivos.size()));
	}
}

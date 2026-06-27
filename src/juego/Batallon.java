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
	private Random rand;

	public Batallon() {
		this.personajes = new ArrayList<>();
		this.usadosEnRonda = new HashMap<>();
		this.rand = new Random();
	}

	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
	}

	public boolean tienePersonajesSaludables() {
		for (Personaje personaje : personajes) {
			if (personaje.estaVivo()) {
				return true;
			}
		}
		return false;
	}

	public void atacar(Batallon enemigo) {
		usadosEnRonda.clear();

		for (Personaje atacante : personajes) {
			if (!atacante.estaVivo()) {
				continue;
			}
			if (!enemigo.tienePersonajesSaludables()) {
				break;
			}

			boolean podiaActuar = atacante.puedeActuar();
			atacante.iniciarTurno();

			if (!atacante.estaVivo()) {
				notificarCaida(atacante);
				continue;
			}
			if (!podiaActuar) {
				System.out.println(atacante.getNombre() + " está paralizado y pierde el turno");
				continue;
			}

			Hechizo hechizo = elegirHechizo(atacante);
			if (hechizo == null) {
				continue;
			}
			registrarUso(atacante, hechizo);

			Personaje objetivo = elegirObjetivo(enemigo);
			if (objetivo == null) {
				continue;
			}
			boolean objetivoVivoAntes = objetivo.estaVivo();

			hechizo.ejecutar(atacante, objetivo);

			if (objetivoVivoAntes && !objetivo.estaVivo()) {
				enemigo.notificarCaida(objetivo);
			}
		}
	}

	public void notificarCaida(Personaje caido) {
		for (Personaje personaje : personajes) {
			if (personaje != caido && personaje.estaVivo()) {
				personaje.alCaerAliado(caido);
			}
		}
	}

	private Hechizo elegirHechizo(Personaje atacante) {
		Set<Hechizo> usados = usadosEnRonda.get(atacante);
		if (usados == null) {
			usados = new HashSet<>();
		}
		List<Hechizo> disponibles = new ArrayList<>();
		for (Hechizo hechizo : atacante.getHechizos()) {
			if (!usados.contains(hechizo)) {
				disponibles.add(hechizo);
			}
		}
		if (disponibles.isEmpty()) {
			return null;
		}
		return disponibles.get(rand.nextInt(disponibles.size()));
	}

	private void registrarUso(Personaje atacante, Hechizo hechizo) {
		if (!usadosEnRonda.containsKey(atacante)) {
			usadosEnRonda.put(atacante, new HashSet<>());
		}
		usadosEnRonda.get(atacante).add(hechizo);
	}

	private Personaje elegirObjetivo(Batallon enemigo) {
		List<Personaje> vivos = new ArrayList<>();
		for (Personaje personaje : enemigo.personajes) {
			if (personaje.estaVivo()) {
				vivos.add(personaje);
			}
		}
		if (vivos.isEmpty()) {
			return null;
		}
		return vivos.get(rand.nextInt(vivos.size()));
	}
}

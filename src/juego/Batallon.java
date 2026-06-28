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
	private Set<String> hechizosUsadosEnTurno;
	private Map<Personaje, List<Hechizo>> historialHechizos;
	private List<String> secuenciaAcciones;
	private Random rand;

	public Batallon() {
		this.personajes = new ArrayList<>();
		this.hechizosUsadosEnTurno = new HashSet<>();
		this.historialHechizos = new HashMap<>();
		this.secuenciaAcciones = new ArrayList<>();
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
		hechizosUsadosEnTurno.clear();

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
				String logInfo = atacante.getNombre() + " está paralizado y pierde el turno";
				System.out.println(logInfo);
				secuenciaAcciones.add(logInfo);
				continue;
			}

			Hechizo hechizo = elegirHechizo(atacante);
			if (hechizo == null) {
				String logInfo = atacante.getNombre() + " no tiene hechizos disponibles para lanzar en este turno";
				System.out.println(logInfo);
				secuenciaAcciones.add(logInfo);
				continue;
			}
			registrarUso(atacante, hechizo);

			Personaje objetivo = hechizo.seleccionarObjetivo(atacante, this.personajes, enemigo.personajes);
			if (objetivo == null) {
				continue;
			}
			boolean objetivoVivoAntes = objetivo.estaVivo();

			hechizo.ejecutar(atacante, objetivo);
			secuenciaAcciones.add(atacante.getNombre() + " lanzó " + hechizo.nombre() + " a " + objetivo.getNombre());

			if (objetivoVivoAntes && !objetivo.estaVivo()) {
				enemigo.notificarCaida(objetivo);
			}
		}
	}

	public void notificarCaida(Personaje caido) {
		String logInfo = "¡" + caido.getNombre() + " ha caído en combate!";
		secuenciaAcciones.add(logInfo);
		for (Personaje personaje : personajes) {
			if (personaje != caido && personaje.estaVivo()) {
				personaje.alCaerAliado(caido);
			}
		}
	}

	private Hechizo elegirHechizo(Personaje atacante) {
		List<Hechizo> disponibles = new ArrayList<>();
		for (Hechizo hechizo : atacante.getHechizos()) {
			if (!hechizosUsadosEnTurno.contains(hechizo.nombre().toLowerCase())) {
				disponibles.add(hechizo);
			}
		}
		if (disponibles.isEmpty()) {
			return null;
		}
		return disponibles.get(rand.nextInt(disponibles.size()));
	}

	private void registrarUso(Personaje atacante, Hechizo hechizo) {
		hechizosUsadosEnTurno.add(hechizo.nombre().toLowerCase());
		historialHechizos.computeIfAbsent(atacante, k -> new ArrayList<>()).add(hechizo);
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public Map<Personaje, List<Hechizo>> getHistorialHechizos() {
		return historialHechizos;
	}

	public List<String> getSecuenciaAcciones() {
		return secuenciaAcciones;
	}
}

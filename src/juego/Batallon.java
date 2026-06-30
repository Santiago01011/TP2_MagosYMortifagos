package juego;

import efectos.EfectoEstado;
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

			String logInfo = hechizo.ejecutar(atacante, objetivo);
			System.out.println(logInfo);
			secuenciaAcciones.add(logInfo);

			if (objetivoVivoAntes && !objetivo.estaVivo()) {
				enemigo.notificarCaida(objetivo);
			}
		}
	}

	public void notificarCaida(Personaje caido) {
		String logInfo = "¡" + caido.getNombre() + " ha caído en combate!";
		System.out.println(logInfo);
		secuenciaAcciones.add(logInfo);
		for (Personaje personaje : personajes) {
			if (personaje != caido && personaje.estaVivo()) {
				String reaccion = personaje.reaccionAlCaerAliado(caido);
				if (!reaccion.isBlank()) {
					System.out.println(reaccion);
					secuenciaAcciones.add(reaccion);
				}
				personaje.alCaerAliado();
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

	public void mostrarEstado(String nombre) {
		System.out.println("Estado del batallón " + nombre + ":");
		for (Personaje personaje : personajes) {
			if (personaje.estaVivo()) {
				String linea = "  " + personaje.getNombre() + " - Vida: " + personaje.getPuntosDeVida()
						+ " - Magia: " + personaje.getNivelMagia();
				List<EfectoEstado> efectos = personaje.getEfectos();
				if (!efectos.isEmpty()) {
					StringBuilder nombresEfectos = new StringBuilder();
					for (int i = 0; i < efectos.size(); i++) {
						if (i > 0) {
							nombresEfectos.append(", ");
						}
						nombresEfectos.append(efectos.get(i).getNombre());
					}
					linea += " - Efectos: " + nombresEfectos;
				}
				System.out.println(linea);
			} else {
				System.out.println("  " + personaje.getNombre() + " - Caído");
			}
		}
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

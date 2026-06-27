package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import efectos.Escudo;
import efectos.Paralizacion;
import hechizos.Expelliarmus;
import hechizos.Hechizo;
import objetos.Capa;

class PersonajeTest {
    private PersonajeStub personaje;

    @BeforeEach
    void setUp() {
        personaje = new PersonajeStub("Test", 50, 100);
    }

    @Test
    void estadoInicial_vidaCompleta() {
        assertEquals(100, personaje.getPuntosDeVida());
    }

    @Test
    void estadoInicial_estaVivo() {
        assertTrue(personaje.estaVivo());
    }

    @Test
    void estadoInicial_nombreCorrecto() {
        assertEquals("Test", personaje.getNombre());
    }

    @Test
    void estadoInicial_sinHechizos() {
        assertTrue(personaje.getHechizos().isEmpty());
    }

    @Test
    void recibirDaño_reduceVida() {
        personaje.recibirDaño(30);
        assertEquals(70, personaje.getPuntosDeVida());
    }

    @Test
    void recibirDaño_noBajaDeCero() {
        personaje.recibirDaño(150);
        assertEquals(0, personaje.getPuntosDeVida());
    }

    @Test
    void recibirDaño_mataPersonaje() {
        personaje.recibirDaño(100);
        assertFalse(personaje.estaVivo());
    }

    @Test
    void recibirDañoDirecto_ignoraEscudo() {
        personaje.aplicarEfecto(new Escudo(50, 1));
        personaje.recibirDañoDirecto(40);
        assertEquals(60, personaje.getPuntosDeVida());
    }

    @Test
    void curar_aumentaVida() {
        personaje.recibirDaño(50);
        personaje.curar(30);
        assertEquals(80, personaje.getPuntosDeVida());
    }

    @Test
    void curar_noExcedeVidaMax() {
        personaje.recibirDaño(10);
        personaje.curar(50);
        assertEquals(100, personaje.getPuntosDeVida());
    }

    @Test
    void curar_desdeCeroVida() {
        personaje.recibirDaño(100);
        personaje.curar(40);
        assertEquals(40, personaje.getPuntosDeVida());
    }

    @Test
    void escudo_reduceDañoMientrasActivo() {
        personaje.aplicarEfecto(new Escudo(50, 1));
        personaje.recibirDaño(40);
        assertEquals(80, personaje.getPuntosDeVida());
    }

    @Test
    void escudo_expiraTrasIniciarTurno() {
        personaje.aplicarEfecto(new Escudo(50, 1));
        personaje.iniciarTurno();
        personaje.recibirDaño(40);
        assertEquals(60, personaje.getPuntosDeVida());
    }

    @Test
    void paralizacion_impideActuar() {
        personaje.aplicarEfecto(new Paralizacion(1));
        assertFalse(personaje.puedeActuar());
    }

    @Test
    void paralizacion_seConsumeTrasUnTurno() {
        personaje.aplicarEfecto(new Paralizacion(1));
        personaje.iniciarTurno();
        assertTrue(personaje.puedeActuar());
    }

    @Test
    void capa_anulaElProximoDaño() {
        personaje.equipar(new Capa());
        personaje.recibirDaño(40);
        assertEquals(100, personaje.getPuntosDeVida());
    }

    @Test
    void capa_soloAnulaUnaVez() {
        personaje.equipar(new Capa());
        personaje.recibirDaño(40);
        personaje.recibirDaño(30);
        assertEquals(70, personaje.getPuntosDeVida());
    }

    @Test
    void agregarHechizo_agregaALaLista() {
        Hechizo h = new Expelliarmus();
        personaje.agregarHechizo(h);
        assertEquals(1, personaje.getHechizos().size());
        assertTrue(personaje.getHechizos().contains(h));
    }

    @Test
    void agregarHechizo_agregaMultiples() {
        Hechizo h1 = new Expelliarmus();
        Hechizo h2 = new Expelliarmus();
        personaje.agregarHechizo(h1);
        personaje.agregarHechizo(h2);
        assertEquals(2, personaje.getHechizos().size());
    }
}

package hechizos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import efectos.Escudo;
import personajes.PersonajeStub;

class HechizoAtaqueTest {
    private PersonajeStub lanzador;
    private PersonajeStub objetivo;

    @BeforeEach
    void setUp() {
        lanzador = new PersonajeStub("Lanzador", 50, 100);
        objetivo = new PersonajeStub("Objetivo", 30, 100);
    }


    @Test
    void expelliarmus_aplicaDañoBase20() {
        Hechizo expelliarmus = new Expelliarmus();
        expelliarmus.ejecutar(lanzador, objetivo);
        assertEquals(80, objetivo.getPuntosDeVida());
    }

    @Test
    void expelliarmus_conBonusAtaque_aplicaDañoMasBonus() {
        lanzador.setBonusAtaqueFijo(10);
        Hechizo expelliarmus = new Expelliarmus();
        expelliarmus.ejecutar(lanzador, objetivo);
        assertEquals(70, objetivo.getPuntosDeVida());
    }

    @Test
    void expelliarmus_noEsOscuridad() {
        Hechizo expelliarmus = new Expelliarmus();
        assertFalse(expelliarmus.esOscuridad());
    }

    @Test
    void expelliarmus_nombreCorrecto() {
        Hechizo expelliarmus = new Expelliarmus();
        assertEquals("Expelliarmus", expelliarmus.nombre());
    }


    @Test
    void avadaKedavra_aplicaDañoBase100() {
        objetivo = new PersonajeStub("Objetivo", 30, 200);
        Hechizo avada = new AvadaKedavra();
        avada.ejecutar(lanzador, objetivo);
        assertEquals(100, objetivo.getPuntosDeVida());
    }

    @Test
    void avadaKedavra_mataCon100Daño() {
        Hechizo avada = new AvadaKedavra();
        avada.ejecutar(lanzador, objetivo);
        assertEquals(0, objetivo.getPuntosDeVida());
        assertFalse(objetivo.estaVivo());
    }

    @Test
    void avadaKedavra_esOscuridad() {
        Hechizo avada = new AvadaKedavra();
        assertTrue(avada.esOscuridad());
    }

    @Test
    void avadaKedavra_nombreCorrecto() {
        Hechizo avada = new AvadaKedavra();
        assertEquals("Avada Kedavra", avada.nombre());
    }

    @Test
    void crucio_aplicaDañoBase30() {
        Hechizo crucio = new Crucio();
        crucio.ejecutar(lanzador, objetivo);
        assertEquals(70, objetivo.getPuntosDeVida());
    }

    @Test
    void crucio_aplicaEfectoEstadoDolor() {
        Hechizo crucio = new Crucio();
        crucio.ejecutar(lanzador, objetivo);
        assertEquals(70, objetivo.getPuntosDeVida());
    }

    @Test
    void crucio_esOscuridad() {
        Hechizo crucio = new Crucio();
        assertTrue(crucio.esOscuridad());
    }

    @Test
    void crucio_nombreCorrecto() {
        Hechizo crucio = new Crucio();
        assertEquals("Crucio", crucio.nombre());
    }

    @Test
    void hechizoAtaque_conEscudo_reduceDaño() {
        objetivo.aplicarEfecto(new Escudo(50, 1));
        Hechizo expelliarmus = new Expelliarmus();
        expelliarmus.ejecutar(lanzador, objetivo);
        assertEquals(90, objetivo.getPuntosDeVida());
    }

    @Test
    void hechizoAtaque_escudoExpiraTrasIniciarTurno() {
        objetivo.aplicarEfecto(new Escudo(50, 1));
        Hechizo expelliarmus = new Expelliarmus();
        expelliarmus.ejecutar(lanzador, objetivo);
        objetivo.iniciarTurno();
        expelliarmus.ejecutar(lanzador, objetivo);
        assertEquals(70, objetivo.getPuntosDeVida());
    }

    @Test
    void hechizoAtaque_dañoNoBajaDeCero() {
        objetivo = new PersonajeStub("Objetivo", 30, 10);
        Hechizo expelliarmus = new Expelliarmus();
        expelliarmus.ejecutar(lanzador, objetivo);
        assertEquals(0, objetivo.getPuntosDeVida());
    }
}

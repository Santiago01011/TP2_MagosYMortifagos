package hechizos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.PersonajeStub;

class HechizoDefensaTest {
    private PersonajeStub lanzador;
    private PersonajeStub objetivo;

    @BeforeEach
    void setUp() {
        lanzador = new PersonajeStub("Lanzador", 50, 100);
        objetivo = new PersonajeStub("Objetivo", 30, 100);
    }

    @Test
    void protego_aplicaEscudoAlLanzador() {
        Hechizo protego = new Protego();
        protego.ejecutar(lanzador, objetivo);

        objetivo.recibirDaño(20);
        assertEquals(80, objetivo.getPuntosDeVida());

        lanzador.recibirDaño(20);
        assertEquals(90, lanzador.getPuntosDeVida());
    }

    @Test
    void protego_noEsOscuridad() {
        Hechizo protego = new Protego();
        assertFalse(protego.esOscuridad());
    }

    @Test
    void protego_nombreCorrecto() {
        Hechizo protego = new Protego();
        assertEquals("Protego", protego.nombre());
    }

    @Test
    void protego_escudoExpiraTrasUnTurno() {
        Hechizo protego = new Protego();
        protego.ejecutar(lanzador, objetivo);

        lanzador.recibirDaño(20);
        assertEquals(90, lanzador.getPuntosDeVida());

        lanzador.iniciarTurno();
        lanzador.recibirDaño(20);
        assertEquals(70, lanzador.getPuntosDeVida());
    }

    @Test
    void protegoMaxima_escudoDuraDosTurnos() {
        Hechizo protegoMaxima = new ProtegoMaxima();
        protegoMaxima.ejecutar(lanzador, objetivo);

        lanzador.recibirDaño(20);
        assertEquals(90, lanzador.getPuntosDeVida());

        lanzador.iniciarTurno();
        lanzador.recibirDaño(20);
        assertEquals(80, lanzador.getPuntosDeVida());

        lanzador.iniciarTurno();
        lanzador.recibirDaño(20);
        assertEquals(60, lanzador.getPuntosDeVida());
    }

    @Test
    void protegoMaxima_noEsOscuridad() {
        Hechizo protegoMaxima = new ProtegoMaxima();
        assertFalse(protegoMaxima.esOscuridad());
    }

    @Test
    void protegoMaxima_nombreCorrecto() {
        Hechizo protegoMaxima = new ProtegoMaxima();
        assertEquals("Protego Maxima", protegoMaxima.nombre());
    }
}

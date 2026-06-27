package efectos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.PersonajeStub;

class EfectoEstadoTest {
    private PersonajeStub personaje;

    @BeforeEach
    void setUp() {
        personaje = new PersonajeStub("Test", 50, 100);
    }

    @Test
    void sangrado_aplicaDañoCadaTurno() {
        personaje.aplicarEfecto(new Sangrado(10, 2));
        personaje.iniciarTurno();
        assertEquals(90, personaje.getPuntosDeVida());
        personaje.iniciarTurno();
        assertEquals(80, personaje.getPuntosDeVida());
    }

    @Test
    void sangrado_expiraTrasSusTurnos() {
        personaje.aplicarEfecto(new Sangrado(10, 2));
        personaje.iniciarTurno();
        personaje.iniciarTurno();
        personaje.iniciarTurno();
        assertEquals(80, personaje.getPuntosDeVida());
    }

    @Test
    void regeneracion_curaCadaTurno() {
        personaje.recibirDaño(50);
        personaje.aplicarEfecto(new Regeneracion(10, 2));
        personaje.iniciarTurno();
        assertEquals(60, personaje.getPuntosDeVida());
        personaje.iniciarTurno();
        assertEquals(70, personaje.getPuntosDeVida());
    }

    @Test
    void paralizacion_impideActuarYLuegoSeConsume() {
        personaje.aplicarEfecto(new Paralizacion(1));
        assertFalse(personaje.puedeActuar());
        personaje.iniciarTurno();
        assertTrue(personaje.puedeActuar());
    }

    @Test
    void escudo_reduceDañoYLuegoExpira() {
        personaje.aplicarEfecto(new Escudo(50, 1));
        personaje.recibirDaño(40);
        assertEquals(80, personaje.getPuntosDeVida());
        personaje.iniciarTurno();
        personaje.recibirDaño(40);
        assertEquals(40, personaje.getPuntosDeVida());
    }

    @Test
    void variosEfectos_convivenEnElMismoPersonaje() {
        personaje.aplicarEfecto(new Sangrado(10, 3));
        personaje.aplicarEfecto(new Escudo(50, 3));
        personaje.recibirDaño(40);
        assertEquals(80, personaje.getPuntosDeVida());
        personaje.iniciarTurno();
        assertEquals(70, personaje.getPuntosDeVida());
    }
}

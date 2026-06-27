package hechizos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personajes.PersonajeStub;

class HechizoCuracionTest {
    private PersonajeStub lanzador;
    private PersonajeStub objetivo;

    @BeforeEach
    void setUp() {
        lanzador = new PersonajeStub("Lanzador", 50, 100);
        objetivo = new PersonajeStub("Objetivo", 30, 100);
    }

    // ── Expecto Patronum ──

    @Test
    void expectoPatronum_curaBase40() {
        objetivo.recibirDaño(60); // 40 HP restantes
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 40 + 40 = 80
        assertEquals(80, objetivo.getPuntosDeVida());
    }

    @Test
    void expectoPatronum_conBonusCuracion_curaMas() {
        lanzador.setBonusCuracionFijo(15);
        objetivo.recibirDaño(60); // 40 HP restantes
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 40 + 40 + 15 = 95
        assertEquals(95, objetivo.getPuntosDeVida());
    }

    @Test
    void expectoPatronum_noExcedeVidaMax() {
        objetivo.recibirDaño(10); // 90 HP restantes
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 90 + 40 = 130, pero vidaMax = 100
        assertEquals(100, objetivo.getPuntosDeVida());
    }

    @Test
    void expectoPatronum_noEsOscuridad() {
        Hechizo expecto = new ExpectoPatronum();
        assertFalse(expecto.esOscuridad());
    }

    @Test
    void expectoPatronum_nombreCorrecto() {
        Hechizo expecto = new ExpectoPatronum();
        assertEquals("Expecto Patronum", expecto.nombre());
    }

    @Test
    void curacion_curaPersonajeConCeroVida() {
        objetivo.recibirDaño(100); // 0 HP
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 0 + 40 = 40
        assertEquals(40, objetivo.getPuntosDeVida());
    }
}

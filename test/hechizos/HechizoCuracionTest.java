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
    void expectoPatronum_curaBase100() {
        objetivo.recibirDaño(90); // 10 HP restantes
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 10 + 100 = 110, capped at 100
        assertEquals(100, objetivo.getPuntosDeVida());
    }

    @Test
    void expectoPatronum_conBonusCuracion_curaMas() {
        objetivo = new PersonajeStub("Objetivo", 30, 200);
        lanzador.setBonusCuracionFijo(15);
        objetivo.recibirDaño(150); // 50 HP restantes
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 50 + 100 + 15 = 165
        assertEquals(165, objetivo.getPuntosDeVida());
    }

    @Test
    void expectoPatronum_noExcedeVidaMax() {
        objetivo.recibirDaño(10); // 90 HP restantes
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // 90 + 100 = 190, pero vidaMax = 100
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
    void curacion_noCuraPersonajeConCeroVida() {
        objetivo.recibirDaño(100); // 0 HP
        Hechizo expecto = new ExpectoPatronum();
        expecto.ejecutar(lanzador, objetivo);
        // No debe curarse porque está eliminado (0 HP)
        assertEquals(0, objetivo.getPuntosDeVida());
    }
}

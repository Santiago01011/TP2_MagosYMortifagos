package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

class SeguidorTest {
    private Seguidor seguidor;

    @BeforeEach
    void setUp() {
        seguidor = new Seguidor("Wormtail", 50, 100);
    }

    // ── aplicarBonusAtaque ──

    @Test
    void bonusAtaque_hechizoOscuro_sumaMortifagoMasBonus() {
        Hechizo avada = new AvadaKedavra();
        int resultado = seguidor.aplicarBonusAtaque(100, avada);
        // Mortifago: 100 + (50/2) = 100 + 25 = 125
        // Seguidor oscuro: 125 + (50/5) = 125 + 10 = 135
        assertEquals(135, resultado);
    }

    @Test
    void bonusAtaque_hechizoNormal_soloMortifago() {
        Hechizo expelliarmus = new Expelliarmus();
        int resultado = seguidor.aplicarBonusAtaque(20, expelliarmus);
        // Mortifago no da bonus con normal: 20
        // Seguidor normal: 20 + 0 = 20
        assertEquals(20, resultado);
    }

    // ── aplicarBonusCuracion ──

    @Test
    void bonusCuracion_usaMortifagoPadre() {
        int resultado = seguidor.aplicarBonusCuracion(40);
        // Mortifago: 40 / 2 = 20
        assertEquals(20, resultado);
    }
}

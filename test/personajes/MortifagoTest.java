package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

class MortifagoTest {
    private Mortifago mortifago;

    @BeforeEach
    void setUp() {
        mortifago = new Mortifago("Voldemort", 60, 120);
    }

    // ── aplicarBonusAtaque ──

    @Test
    void bonusAtaque_hechizoOscuro_agregaNivelMagiaDiv2() {
        Hechizo avada = new AvadaKedavra();
        int resultado = mortifago.aplicarBonusAtaque(100, avada);
        // 100 + (60 / 2) = 100 + 30 = 130
        assertEquals(130, resultado);
    }

    @Test
    void bonusAtaque_hechizoNormal_noAgregaBonus() {
        Hechizo expelliarmus = new Expelliarmus();
        int resultado = mortifago.aplicarBonusAtaque(20, expelliarmus);
        // Mortífagos no tienen bonus con hechizos normales
        assertEquals(20, resultado);
    }

    // ── aplicarBonusCuracion ──

    @Test
    void bonusCuracion_reduceAMitad() {
        int resultado = mortifago.aplicarBonusCuracion(40);
        // 40 / 2 = 20
        assertEquals(20, resultado);
    }

    @Test
    void bonusCuracion_curacionBaja() {
        int resultado = mortifago.aplicarBonusCuracion(10);
        // 10 / 2 = 5
        assertEquals(5, resultado);
    }
}

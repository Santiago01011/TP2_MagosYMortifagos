package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

class MagoTest {
    private Mago mago;

    @BeforeEach
    void setUp() {
        mago = new Mago("Harry", 50, 100);
    }

    // ── aplicarBonusAtaque ──

    @Test
    void bonusAtaque_hechizoNormal_agregaNivelMagiaDiv5() {
        Hechizo expelliarmus = new Expelliarmus();
        int resultado = mago.aplicarBonusAtaque(20, expelliarmus);
        // 20 + (50 / 5) = 20 + 10 = 30
        assertEquals(30, resultado);
    }

    @Test
    void bonusAtaque_hechizoOscuro_noAgregaBonus() {
        Hechizo avada = new AvadaKedavra();
        int resultado = mago.aplicarBonusAtaque(100, avada);
        // Magos no tienen bonus con hechizos oscuros
        assertEquals(100, resultado);
    }

    // ── aplicarBonusCuracion ──

    @Test
    void bonusCuracion_agregaNivelMagiaDiv3() {
        int resultado = mago.aplicarBonusCuracion(40);
        // 40 + (50 / 3) = 40 + 16 = 56
        assertEquals(56, resultado);
    }

    @Test
    void bonusCuracion_conNivelMagiaBajo() {
        Mago magoDebil = new Mago("Debil", 5, 100);
        int resultado = magoDebil.aplicarBonusCuracion(40);
        // 40 + (5 / 3) = 40 + 1 = 41
        assertEquals(41, resultado);
    }
}

package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

class AurorTest {
    private Auror auror;

    @BeforeEach
    void setUp() {
        auror = new Auror("Kingsley", 60, 100);
    }

    // ── aplicarBonusAtaque ──

    @Test
    void bonusAtaque_hechizoNormal_sumaMagoMasEspecialista() {
        Hechizo expelliarmus = new Expelliarmus();
        int resultado = auror.aplicarBonusAtaque(20, expelliarmus);
        // Mago: 20 + (60/5) = 20 + 12 = 32
        // Auror: 32 + (60/3) = 32 + 20 = 52
        assertEquals(52, resultado);
    }

    @Test
    void bonusAtaque_hechizoOscuro_soloEspecialista() {
        Hechizo avada = new AvadaKedavra();
        int resultado = auror.aplicarBonusAtaque(100, avada);
        // Mago no da bonus con oscuro: 100
        // Auror: 100 + (60/3) = 100 + 20 = 120
        assertEquals(120, resultado);
    }

    // ── aplicarBonusCuracion ──

    @Test
    void bonusCuracion_usaMagoPadre() {
        int resultado = auror.aplicarBonusCuracion(40);
        // Mago: 40 + (60/3) = 40 + 20 = 60
        assertEquals(60, resultado);
    }
}

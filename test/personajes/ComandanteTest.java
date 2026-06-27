package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Crucio;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

class ComandanteTest {
    private Comandante comandante;

    @BeforeEach
    void setUp() {
        comandante = new Comandante("Bellatrix", 60, 100);
    }

    // ── aplicarBonusAtaque ──

    @Test
    void bonusAtaque_hechizoOscuro_sumaMortifagoMasEspecialista() {
        Hechizo avada = new AvadaKedavra();
        int resultado = comandante.aplicarBonusAtaque(100, avada);
        // Mortifago: 100 + (60/2) = 100 + 30 = 130
        // Comandante oscuro: 130 + (60/2) = 130 + 30 = 160
        assertEquals(160, resultado);
    }

    @Test
    void bonusAtaque_hechizoNormal_mortifagoSinBonusMasBonusMenor() {
        Hechizo expelliarmus = new Expelliarmus();
        int resultado = comandante.aplicarBonusAtaque(20, expelliarmus);
        // Mortifago no da bonus con normal: 20
        // Comandante normal: 20 + (60/6) = 20 + 10 = 30
        assertEquals(30, resultado);
    }

    @Test
    void bonusAtaque_crucio_hechizoOscuro() {
        Hechizo crucio = new Crucio();
        int resultado = comandante.aplicarBonusAtaque(30, crucio);
        // Mortifago: 30 + (60/2) = 30 + 30 = 60
        // Comandante oscuro: 60 + (60/2) = 60 + 30 = 90
        assertEquals(90, resultado);
    }

    // ── aplicarBonusCuracion ──

    @Test
    void bonusCuracion_maxConCero() {
        // Mortifago: curacionBase / 2
        // Comandante: max(0, Mortifago bonus)
        int resultado = comandante.aplicarBonusCuracion(40);
        // Mortifago: 40 / 2 = 20
        // Comandante: max(0, 20) = 20
        assertEquals(20, resultado);
    }

    @Test
    void bonusCuracion_conCuracionBaja() {
        int resultado = comandante.aplicarBonusCuracion(1);
        // Mortifago: 1 / 2 = 0
        // Comandante: max(0, 0) = 0
        assertEquals(0, resultado);
    }
}

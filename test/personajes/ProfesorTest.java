package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

class ProfesorTest {
    private Profesor profesor;

    @BeforeEach
    void setUp() {
        profesor = new Profesor("Dumbledore", 80, 100);
    }

    // ── aplicarBonusAtaque ──

    @Test
    void bonusAtaque_hechizoNormal_sumaMagoMasVersatil() {
        Hechizo expelliarmus = new Expelliarmus();
        int resultado = profesor.aplicarBonusAtaque(20, expelliarmus);
        // Mago: 20 + (80/5) = 20 + 16 = 36
        // Profesor: 36 + (80/4) = 36 + 20 = 56
        assertEquals(56, resultado);
    }

    @Test
    void bonusAtaque_hechizoOscuro_soloVersatil() {
        Hechizo avada = new AvadaKedavra();
        int resultado = profesor.aplicarBonusAtaque(100, avada);
        // Mago no da bonus con oscuro: 100
        // Profesor: 100 + (80/4) = 100 + 20 = 120
        assertEquals(120, resultado);
    }

    // ── aplicarBonusCuracion ──

    @Test
    void bonusCuracion_mejorCurador() {
        int resultado = profesor.aplicarBonusCuracion(40);
        // Profesor: 40 + (80/2) = 40 + 40 = 80
        // (NO usa super.aplicarBonusCuracion, tiene su propia implementación)
        assertEquals(80, resultado);
    }

    @Test
    void bonusCuracion_nivelMagiaBajo() {
        Profesor profesorDebil = new Profesor("Debil", 10, 100);
        int resultado = profesorDebil.aplicarBonusCuracion(40);
        // 40 + (10/2) = 40 + 5 = 45
        assertEquals(45, resultado);
    }
}

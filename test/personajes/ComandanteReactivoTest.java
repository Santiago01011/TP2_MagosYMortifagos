package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.Hechizo;

class ComandanteReactivoTest {
    private Comandante comandante;
    private Hechizo oscuro;

    @BeforeEach
    void setUp() {
        comandante = new Comandante("Bellatrix", 60, 100);
        oscuro = new AvadaKedavra();
    }

    @Test
    void sinFuria_bonusNormal() {
        assertEquals(160, comandante.aplicarBonusAtaque(100, oscuro));
    }

    @Test
    void alCaerAliado_activaFuria() {
        Personaje aliado = new Seguidor("Colagusano", 30, 1);
        comandante.alCaerAliado(aliado);
        assertTrue(comandante.tieneFuriaActiva());
    }

    @Test
    void furia_potenciaElProximoAtaque() {
        Personaje aliado = new Seguidor("Colagusano", 30, 1);
        comandante.alCaerAliado(aliado);
        assertEquals(220, comandante.aplicarBonusAtaque(100, oscuro));
    }

    @Test
    void furia_seConsumeTrasUnAtaque() {
        Personaje aliado = new Seguidor("Colagusano", 30, 1);
        comandante.alCaerAliado(aliado);
        comandante.aplicarBonusAtaque(100, oscuro);
        assertFalse(comandante.tieneFuriaActiva());
        assertEquals(160, comandante.aplicarBonusAtaque(100, oscuro));
    }

    @Test
    void comandanteMuerto_noEntraEnFuria() {
        comandante.recibirDaño(100);
        Personaje aliado = new Seguidor("Colagusano", 30, 1);
        comandante.alCaerAliado(aliado);
        assertFalse(comandante.tieneFuriaActiva());
    }
}

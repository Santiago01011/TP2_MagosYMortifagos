package objetos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.Expelliarmus;
import hechizos.Hechizo;
import personajes.PersonajeStub;

class ObjetoMagicoTest {
    private PersonajeStub personaje;

    @BeforeEach
    void setUp() {
        personaje = new PersonajeStub("Test", 50, 100);
    }

    @Test
    void varita_potenciaElAtaque() {
        Hechizo hechizo = new Expelliarmus();
        Varita varita = new Varita(15);
        assertEquals(35, varita.modificarAtaque(20, hechizo));
    }

    @Test
    void varita_equipada_aumentaBonusDeAtaque() {
        personaje.equipar(new Varita(15));
        int resultado = personaje.aplicarBonusObjetosAtaque(20, new Expelliarmus());
        assertEquals(35, resultado);
    }

    @Test
    void capa_anulaUnSoloAtaque() {
        Capa capa = new Capa();
        assertEquals(0, capa.alRecibirDaño(40));
        assertEquals(30, capa.alRecibirDaño(30));
    }

    @Test
    void capa_equipada_anulaProximoDaño() {
        personaje.equipar(new Capa());
        personaje.recibirDaño(40);
        assertEquals(100, personaje.getPuntosDeVida());
    }

    @Test
    void amuleto_recuperaMasMagiaAlDescansar() {
        Amuleto amuleto = new Amuleto(10);
        assertEquals(35, amuleto.modificarDescanso(25));
    }

    @Test
    void amuleto_equipado_descansarSumaExtra() {
        personaje.equipar(new Amuleto(10));
        int recuperado = personaje.descansar(20);
        assertEquals(30, recuperado);
        assertEquals(80, personaje.getNivelMagia());
    }

    @Test
    void quitar_dejaDeAplicarElObjeto() {
        Capa capa = new Capa();
        personaje.equipar(capa);
        personaje.quitar(capa);
        personaje.recibirDaño(40);
        assertEquals(60, personaje.getPuntosDeVida());
    }
}

package hechizos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ForjadorHechizosTest {

    @Test
    void crear_expelliarmus() {
        Hechizo h = ForjadorHechizos.crear("expelliarmus");
        assertInstanceOf(Expelliarmus.class, h);
        assertEquals("Expelliarmus", h.nombre());
    }

    @Test
    void crear_avadaKedavra() {
        Hechizo h = ForjadorHechizos.crear("avada kedavra");
        assertInstanceOf(AvadaKedavra.class, h);
        assertTrue(h.esOscuridad());
    }

    @Test
    void crear_crucio() {
        Hechizo h = ForjadorHechizos.crear("crucio");
        assertInstanceOf(Crucio.class, h);
        assertTrue(h.esOscuridad());
    }

    @Test
    void crear_protego() {
        Hechizo h = ForjadorHechizos.crear("protego");
        assertInstanceOf(Protego.class, h);
    }

    @Test
    void crear_protegoMaxima() {
        Hechizo h = ForjadorHechizos.crear("protego maxima");
        assertInstanceOf(ProtegoMaxima.class, h);
    }

    @Test
    void crear_expectoPatronum() {
        Hechizo h = ForjadorHechizos.crear("expecto patronum");
        assertInstanceOf(ExpectoPatronum.class, h);
    }

    @Test
    void crear_caseInsensitive() {
        Hechizo h1 = ForjadorHechizos.crear("EXPELLIARMUS");
        Hechizo h2 = ForjadorHechizos.crear("Expelliarmus");
        Hechizo h3 = ForjadorHechizos.crear("expelliarmus");
        assertAll(
            () -> assertInstanceOf(Expelliarmus.class, h1),
            () -> assertInstanceOf(Expelliarmus.class, h2),
            () -> assertInstanceOf(Expelliarmus.class, h3)
        );
    }

    @Test
    void crear_hechizoDesconocido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            ForjadorHechizos.crear("wingardium leviosa");
        });
    }

    @Test
    void crear_hechizoDesconocido_mensajeCorrecto() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            ForjadorHechizos.crear("lumos");
        });
        assertTrue(ex.getMessage().contains("lumos"));
    }
}

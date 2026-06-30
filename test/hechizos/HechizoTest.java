package hechizos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import personajes.PersonajeStub;

public class HechizoTest {

    @Test
    void testHechizoAtaque_debeReducirVidaDelObjetivo() {
        System.out.println("=== TEST: hechizoAtaque_debeReducirVidaDelObjetivo ===");
        // Arrange
        PersonajeStub lanzador = new PersonajeStub("Harry", 50, 100);
        PersonajeStub objetivo = new PersonajeStub("Voldemort", 30, 100);
        Hechizo expelliarmus = new Expelliarmus();
        System.out.println("  Arrange: Lanzador " + lanzador.getNombre() + " (HP: 100), Objetivo " + objetivo.getNombre() + " (HP: 100), Hechizo: Expelliarmus (Base daño: 80)");

        // Act
        System.out.println("  Act: Ejecutando Expelliarmus...");
        expelliarmus.ejecutar(lanzador, objetivo);

        // Assert
        System.out.println("  Assert: Verificando que la vida del objetivo sea 20 (100 - 80)");
        assertEquals(20, objetivo.getPuntosDeVida());
        System.out.println("  -> SUCCESS. HP del objetivo: " + objetivo.getPuntosDeVida());
    }

    @Test
    void testHechizoDefensa_debeAplicarEscudoAlLanzador() {
        System.out.println("=== TEST: hechizoDefensa_debeAplicarEscudoAlLanzador ===");
        // Arrange
        PersonajeStub lanzador = new PersonajeStub("Harry", 50, 100);
        PersonajeStub objetivo = new PersonajeStub("Voldemort", 30, 100);
        Hechizo protego = new Protego();
        System.out.println("  Arrange: Lanzador " + lanzador.getNombre() + " (HP: 100), Hechizo: Protego (50% reducción)");

        // Act
        System.out.println("  Act: Ejecutando Protego...");
        protego.ejecutar(lanzador, objetivo);
        System.out.println("  Act: Lanzador recibe 20 de daño...");
        lanzador.recibirDaño(20);

        // Assert
        System.out.println("  Assert: Verificando que la vida del lanzador sea 90 (100 - (20 * 0.5))");
        assertEquals(90, lanzador.getPuntosDeVida());
        System.out.println("  -> SUCCESS. HP del lanzador: " + lanzador.getPuntosDeVida());
    }

    @Test
    void testHechizoCuracion_debeRestaurarVidaDelObjetivo() {
        System.out.println("=== TEST: hechizoCuracion_debeRestaurarVidaDelObjetivo ===");
        // Arrange
        PersonajeStub lanzador = new PersonajeStub("Harry", 50, 100);
        PersonajeStub objetivo = new PersonajeStub("Ron", 30, 100);
        objetivo.recibirDaño(50); // HP queda en 50
        Hechizo expecto = new ExpectoPatronum();
        System.out.println("  Arrange: Lanzador " + lanzador.getNombre() + ", Objetivo " + objetivo.getNombre() + " (HP: 50), Hechizo: Expecto Patronum (Base curación: 80)");

        // Act
        System.out.println("  Act: Ejecutando curación...");
        expecto.ejecutar(lanzador, objetivo);

        // Assert
        System.out.println("  Assert: Verificando que la vida del objetivo se haya restaurado a 100");
        assertEquals(100, objetivo.getPuntosDeVida());
        System.out.println("  -> SUCCESS. HP del objetivo: " + objetivo.getPuntosDeVida());
    }

    @Test
    void testClasificacionHechizos_oscurosYNormales() {
        System.out.println("=== TEST: clasificacionHechizos_oscurosYNormales ===");
        // Arrange
        Hechizo avada = new AvadaKedavra();
        Hechizo expelliarmus = new Expelliarmus();
        System.out.println("  Arrange: Avada Kedavra y Expelliarmus creados");

        // Act & Assert
        System.out.println("  Act & Assert: Verificando esOscuridad() para cada uno");
        assertTrue(avada.esOscuridad(), "Avada Kedavra debe ser oscuro");
        assertFalse(expelliarmus.esOscuridad(), "Expelliarmus no debe ser oscuro");
        System.out.println("  -> SUCCESS. Avada es oscuro: " + avada.esOscuridad() + ", Expelliarmus es oscuro: " + expelliarmus.esOscuridad());
    }
}

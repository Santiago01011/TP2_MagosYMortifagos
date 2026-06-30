package juego;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import personajes.Mago;
import personajes.Mortifago;
import personajes.Personaje;
import hechizos.Expelliarmus;

public class BatallaTest {

    @Test
    void testColeccionPersonajes_debeGestionarIntegrantesYVidalidad() {
        System.out.println("=== TEST: coleccionPersonajes_debeGestionarIntegrantesYVidalidad ===");
        // Arrange
        Batallon batallon = new Batallon();
        Personaje mago1 = new Mago("Harry", 50, 100);
        Personaje mago2 = new Mago("Hermione", 60, 90);
        System.out.println("  Arrange: Creado batallón vacío");

        // Act
        System.out.println("  Act: Agregando personajes al batallón...");
        batallon.agregarPersonaje(mago1);
        batallon.agregarPersonaje(mago2);
        boolean tieneVivosInicial = batallon.tienePersonajesSaludables();

        System.out.println("  Act: Infligiendo daño letal a ambos integrantes...");
        mago1.recibirDaño(200);
        mago2.recibirDaño(200);
        boolean tieneVivosFinal = batallon.tienePersonajesSaludables();

        // Assert
        System.out.println("  Assert: Verificando que inicialmente tenía personajes saludables, y al final no");
        assertTrue(tieneVivosInicial);
        assertFalse(tieneVivosFinal);
        System.out.println("  -> SUCCESS. Estado Inicial saludables: " + tieneVivosInicial + ", Estado Final saludables: " + tieneVivosFinal);
    }

    @Test
    void testLogicaBatalla_debeReducirVidaDelEnemigoAlAtacar() {
        System.out.println("=== TEST: logicaBatalla_debeReducirVidaDelEnemigoAlAtacar ===");
        // Arrange
        Batallon aliados = new Batallon();
        Personaje harry = new Mago("Harry", 50, 100);
        harry.agregarHechizo(new Expelliarmus());
        aliados.agregarPersonaje(harry);

        Batallon enemigos = new Batallon();
        Personaje voldemort = new Mortifago("Voldemort", 60, 200);
        enemigos.agregarPersonaje(voldemort);
        
        int vidaInicial = voldemort.getPuntosDeVida();
        System.out.println("  Arrange: Aliados (Harry con Expelliarmus), Enemigos (Voldemort con HP: " + vidaInicial + ")");

        // Act
        System.out.println("  Act: Batallón Aliado inicia ataque contra el Batallón Enemigo...");
        aliados.atacar(enemigos);
        int vidaFinal = voldemort.getPuntosDeVida();

        // Assert
        System.out.println("  Assert: Verificando que Voldemort haya perdido vida");
        assertTrue(vidaFinal < vidaInicial);
        System.out.println("  -> SUCCESS. HP inicial de Voldemort: " + vidaInicial + ", HP final tras recibir hechizo: " + vidaFinal);
    }
}

package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.Hechizo;

public class PersonajeTest {

    @Test
    void testDañoYVida_debeReducirVidaYDetectarMuerte() {
        System.out.println("=== TEST: dañoYVida_debeReducirVidaYDetectarMuerte ===");
        // Arrange
        PersonajeStub personaje = new PersonajeStub("Test", 50, 100);
        System.out.println("  Arrange: Personaje " + personaje.getNombre() + " (HP: 100)");

        // Act
        System.out.println("  Act: Recibiendo 40 de daño...");
        personaje.recibirDaño(40);
        int vidaParcial = personaje.getPuntosDeVida();
        
        System.out.println("  Act: Recibiendo 70 de daño adicional...");
        personaje.recibirDaño(70);
        int vidaFinal = personaje.getPuntosDeVida();
        boolean estaVivo = personaje.estaVivo();

        // Assert
        System.out.println("  Assert: Verificando HP parcial (60), HP final (0) y estado vivo (false)");
        assertEquals(60, vidaParcial);
        assertEquals(0, vidaFinal);
        assertFalse(estaVivo);
        System.out.println("  -> SUCCESS. HP parcial: " + vidaParcial + ", HP final: " + vidaFinal + ", Vivo: " + estaVivo);
    }

    @Test
    void testBonusAuror_debeSumarBonusMagoMasEspecialista() {
        System.out.println("=== TEST: bonusAuror_debeSumarBonusMagoMasEspecialista ===");
        // Arrange
        Auror auror = new Auror("Kingsley", 60, 100);
        Hechizo expelliarmus = new Expelliarmus(); // Hechizo normal
        Hechizo avada = new AvadaKedavra(); // Hechizo oscuro
        System.out.println("  Arrange: Auror con nivel de magia 60");

        // Act
        System.out.println("  Act: Calculando bonus para hechizo normal y oscuro...");
        int dañoNormal = auror.aplicarBonusAtaque(20, expelliarmus);
        int dañoOscuro = auror.aplicarBonusAtaque(100, avada);

        // Assert
        // Normal: 20 + (60/5) + (60/3) = 20 + 12 + 20 = 52
        // Oscuro: 100 + (60/3) = 120 (Mago no da bonus con oscuro, solo Auror)
        System.out.println("  Assert: Verificando daño normal esperado (52) y oscuro esperado (120)");
        assertEquals(52, dañoNormal);
        assertEquals(120, dañoOscuro);
        System.out.println("  -> SUCCESS. Daño Normal: " + dañoNormal + ", Daño Oscuro: " + dañoOscuro);
    }

    @Test
    void testBonusMortifago_debeSumarBonusSoloConHechizosOscuros() {
        System.out.println("=== TEST: bonusMortifago_debeSumarBonusSoloConHechizosOscuros ===");
        // Arrange
        Mortifago mortifago = new Mortifago("Lucius", 60, 100);
        Hechizo expelliarmus = new Expelliarmus();
        Hechizo avada = new AvadaKedavra();
        System.out.println("  Arrange: Mortífago con nivel de magia 60");

        // Act
        System.out.println("  Act: Calculando daño normal y oscuro...");
        int dañoNormal = mortifago.aplicarBonusAtaque(20, expelliarmus);
        int dañoOscuro = mortifago.aplicarBonusAtaque(20, avada);

        // Assert
        // Normal: 20 (sin bonus)
        // Oscuro: 20 + 60 = 80
        System.out.println("  Assert: Verificando daño normal (20) y oscuro (80)");
        assertEquals(20, dañoNormal);
        assertEquals(80, dañoOscuro);
        System.out.println("  -> SUCCESS. Daño Normal: " + dañoNormal + ", Daño Oscuro: " + dañoOscuro);
    }

    @Test
    void testComandanteFuria_debePotenciarAtaqueYConsumirse() {
        System.out.println("=== TEST: comandanteFuria_debePotenciarAtaqueYConsumirse ===");
        // Arrange
        Comandante comandante = new Comandante("Bellatrix", 60, 100);
        Hechizo oscuro = new AvadaKedavra();
        System.out.println("  Arrange: Comandante " + comandante.getNombre() + " (Magia: 60) creado");

        // Act & Assert 1: Damage without Furia
        int dañoNormal = comandante.aplicarBonusAtaque(100, oscuro);
        System.out.println("  Act: Calculando daño base sin furia...");
        assertEquals(190, dañoNormal); // Mortifago (160) + Comandante (30) = 190
        System.out.println("  Assert: Daño sin furia es " + dañoNormal);

        // Act 2: Al caer aliado, entra en furia
        System.out.println("  Act: Se activa la furia al caer un aliado...");
        comandante.alCaerAliado();
        boolean tieneFuria = comandante.tieneFuriaActiva();
        assertTrue(tieneFuria);

        // Act 3: Damage with Furia
        int dañoFuria = comandante.aplicarBonusAtaque(100, oscuro);
        System.out.println("  Assert: Daño con furia debe ser 250 (190 + 60 potencia)");
        assertEquals(250, dañoFuria);

        // Act 4: Furia is consumed after attack
        boolean tieneFuriaDespues = comandante.tieneFuriaActiva();
        System.out.println("  Assert: Furia debe estar consumida después del ataque...");
        assertFalse(tieneFuriaDespues);

        int dañoPostFuria = comandante.aplicarBonusAtaque(100, oscuro);
        assertEquals(190, dañoPostFuria);
        System.out.println("  -> SUCCESS. Furia consumida, daño volvió a: " + dañoPostFuria);
    }

    @Test
    void testColeccionHechizos_debePermitirAgregarYRecuperar() {
        System.out.println("=== TEST: coleccionHechizos_debePermitirAgregarYRecuperar ===");
        // Arrange
        PersonajeStub personaje = new PersonajeStub("Harry", 50, 100);
        Hechizo h1 = new Expelliarmus();
        Hechizo h2 = new AvadaKedavra();
        System.out.println("  Arrange: Creado personaje sin hechizos");

        // Act
        System.out.println("  Act: Agregando Expelliarmus y Avada Kedavra...");
        personaje.agregarHechizo(h1);
        personaje.agregarHechizo(h2);

        // Assert
        System.out.println("  Assert: Verificando que la colección tenga tamaño 2 y contenga ambos");
        assertEquals(2, personaje.getHechizos().size());
        assertTrue(personaje.getHechizos().contains(h1));
        assertTrue(personaje.getHechizos().contains(h2));
        System.out.println("  -> SUCCESS. Colección de hechizos funciona adecuadamente");
    }
}

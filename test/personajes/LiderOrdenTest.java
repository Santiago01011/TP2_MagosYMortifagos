package personajes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import efectos.EfectoEstado;

class LiderOrdenTest {
    private LiderOrden lider;

    @BeforeEach
    void setUp() {
        lider = new LiderOrden("Dumbledore", 70, 200);
    }

    @Test
    void alCaerAliado_aplicaEscudo() {
        Personaje aliado = new Estudiante("Harry", 40, 1);
        
        // Verificar que arranca sin escudos
        assertTrue(lider.efectos.isEmpty());
        
        // Simular caída del aliado
        lider.alCaerAliado();
        
        // El líder debe tener ahora un efecto de tipo Escudo
        boolean tieneEscudo = false;
        for (EfectoEstado efecto : lider.efectos) {
            if (efecto.getNombre().equalsIgnoreCase("Escudo")) {
                tieneEscudo = true;
                break;
            }
        }
        assertTrue(tieneEscudo);
    }

    @Test
    void liderMuerto_noAplicaEscudo() {
        lider.recibirDaño(200);
        assertFalse(lider.estaVivo());
        
        Personaje aliado = new Estudiante("Harry", 40, 1);
        lider.alCaerAliado();
        
        assertTrue(lider.efectos.isEmpty());
    }
}

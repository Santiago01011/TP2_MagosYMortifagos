package personajes;

import hechizos.Hechizo;

/**
 * Stub concreto de Personaje para tests aislados.
 * Permite controlar los bonus de ataque y curación.
 */
public class PersonajeStub extends Personaje {
    private int bonusAtaqueFijo;
    private int bonusCuracionFijo;

    public PersonajeStub(String nombre, int nivelMagia, int puntosDeVida) {
        super(nombre, nivelMagia, puntosDeVida);
        this.bonusAtaqueFijo = 0;
        this.bonusCuracionFijo = 0;
    }

    public void setBonusAtaqueFijo(int bonus) {
        this.bonusAtaqueFijo = bonus;
    }

    public void setBonusCuracionFijo(int bonus) {
        this.bonusCuracionFijo = bonus;
    }

    @Override
    public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
        return dañoBase + bonusAtaqueFijo;
    }

    @Override
    public int aplicarBonusCuracion(int curacionBase) {
        return curacionBase + bonusCuracionFijo;
    }
}

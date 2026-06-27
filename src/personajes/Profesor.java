package personajes;

import hechizos.Hechizo;

public class Profesor extends Mago {

    public Profesor(String nombre, int nivelMagia, int puntosDeVida) {
        super(nombre, nivelMagia, puntosDeVida);
    }

    @Override
    public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
        // Profesores son versátiles: buen ataque y curación
        int base = super.aplicarBonusAtaque(dañoBase, hechizo);
        return base + (nivelMagia / 4);
    }

    @Override
    public int aplicarBonusCuracion(int curacionBase) {
        return curacionBase + (nivelMagia / 2);
    }
}

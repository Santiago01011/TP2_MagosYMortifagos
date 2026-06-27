package personajes;

import hechizos.Hechizo;

public class Comandante extends Mortifago {

    public Comandante(String nombre, int nivelMagia, int puntosDeVida) {
        super(nombre, nivelMagia, puntosDeVida);
    }

    @Override
    public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
        // Comandantes son muy letales con hechizos oscuros
        int base = super.aplicarBonusAtaque(dañoBase, hechizo);
        return base + (hechizo.esOscuridad() ? (nivelMagia / 2) : (nivelMagia / 6));
    }

    @Override
    public int aplicarBonusCuracion(int curacionBase) {
        return Math.max(0, super.aplicarBonusCuracion(curacionBase));
    }
}

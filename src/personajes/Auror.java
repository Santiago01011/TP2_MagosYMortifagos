package personajes;

import hechizos.Hechizo;

public class Auror extends Mago {

    public Auror(String nombre, int nivelMagia, int puntosDeVida) {
        super(nombre, nivelMagia, puntosDeVida);
    }

    @Override
    public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
        // Aurors son especialistas en ataque: mayor bonus general
        int base = super.aplicarBonusAtaque(dañoBase, hechizo);
        return base + (nivelMagia / 3);
    }

    @Override
    public int aplicarBonusCuracion(int curacionBase) {
        // Curación estándar de mago
        return super.aplicarBonusCuracion(curacionBase);
    }
}

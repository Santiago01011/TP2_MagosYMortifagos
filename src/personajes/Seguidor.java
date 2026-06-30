package personajes;

import hechizos.Hechizo;

public class Seguidor extends Mortifago {

    public Seguidor(String nombre, int nivelMagia, int puntosDeVida) {
        super(nombre, nivelMagia, puntosDeVida);
    }

    @Override
    public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
        int base = super.aplicarBonusAtaque(dañoBase, hechizo);
        return base + (hechizo.esOscuridad() ? (nivelMagia / 5) : 0);
    }

}

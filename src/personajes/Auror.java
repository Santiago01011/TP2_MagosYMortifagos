package personajes;

import efectos.Escudo;
import hechizos.Hechizo;

public class Auror extends Mago {

    public Auror(String nombre, int nivelMagia, int puntosDeVida) {
        super(nombre, nivelMagia, puntosDeVida);
    }

    @Override
    public int aplicarBonusAtaque(int dañoBase, Hechizo hechizo) {
        return super.aplicarBonusAtaque(dañoBase, hechizo) + (nivelMagia / 3);
    }

    @Override
    public void alCaerAliado() {
        if (estaVivo()) {
            this.aplicarEfecto(new Escudo(60, 2));
        }
    }

    @Override
    public String reaccionAlCaerAliado(Personaje caido) {
        return estaVivo() ? "  ¡" + nombre + " alza su varita en señal de protección por la caída de " + caido.getNombre() + "!" : "";
    }

}

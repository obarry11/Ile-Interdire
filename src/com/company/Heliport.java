package com.company;

import java.awt.*;

public class Heliport extends Cellule{

    public Heliport(CModele modele, int x, int y) {
        super(modele, x, y);
        this.couleur[2] = Color.black;
        this.couleur[1] = Color.gray;
        this.couleur[0] = Color.white;
    }
    @Override
    public String toString()
    {
        return "Heliport";
    }
}

package com.company;

import java.awt.*;

enum Element {
    AIR,EAU,TERRE,FEU
}

public class Arctfact extends Cellule{

    protected Element element;

    public Arctfact(CModele modele, int x, int y) {
        super(modele, x, y);
        this.couleur[2] = new Color(193, 101, 234);
        this.couleur[1] = Color.PINK;
        this.couleur[0] = Color.MAGENTA;
        int randomElementInt = Utility.getRandomNumber(0,3);
        switch (randomElementInt) {
            case 0:
                this.element = Element.AIR;
                break;
            case 1:
                this.element = Element.EAU;
                break;
            case 2:
                this.element = Element.TERRE;
                break;
            case 3:
                this.element = Element.FEU;
                break;
        }
    }

    @Override
    public String toString()
    {
        return "Artfact";
    }
}

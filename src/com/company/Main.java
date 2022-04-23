package com.company;
import com.company.CModele;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            CModele modele = new CModele();
            CVue vue = new CVue(modele);
        });
    }
}
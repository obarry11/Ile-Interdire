package com.company;

import javax.swing.*;
import java.awt.*;

class CVue {

    private JFrame frame;

    private VueGrille grille;
    private VueCommandes commandes;

    /** Construction d'une vue attachée à un modèle. */
    public CVue(CModele modele) {
	/** Définition de la fenêtre principale. */
	frame = new JFrame();
	frame.setTitle("Jeu de la vie de Conway");

	frame.setLayout(new FlowLayout());

	/** Définition des deux vues et ajout à la fenêtre. */
	grille = new VueGrille(modele);
	frame.add(grille);
	commandes = new VueCommandes(modele);
	frame.add(commandes);

	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//frame.setVisible(true);



		frame.add(commandes);
		frame.setVisible(true);

    }
}


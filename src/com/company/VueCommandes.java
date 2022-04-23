package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

class VueCommandes extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * référence au modèle.
     */
    private CModele modele;

    /** Constructeur. */
    public VueCommandes(CModele modele) {
	this.modele = modele;
	/**
	 * On crée un nouveau bouton, de classe [JButton], en précisant le
	 * texte qui doit l'étiqueter.
	 * Puis on ajoute ce bouton au panneau [this].
	 */
	JButton boutonAvance = new JButton(">");
	this.add(boutonAvance);

		//JPanel g = new JPanel();

		//this.add(g);



	Controleur ctrl = new Controleur(modele);
	/** Enregistrement du contrôleur comme auditeur du bouton. */
	boutonAvance.addActionListener(ctrl);
    boutonAvance.setFocusable(false);


		//KeyListener listener = new MyKeyListener();
		addKeyListener(ctrl);
		 setFocusable(true);


    }

}

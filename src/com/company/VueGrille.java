package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VueGrille extends JPanel implements Observer {
    /** On maintient une référence vers le modèle. */
    private CModele modele;
    /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
    public final static int TAILLE = 12;
	public Controleur crtl;

    /** Constructeur. */
    public VueGrille(CModele modele) {
	this.modele = modele;
	/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
	modele.addObserver(this);
	/**
	 * Définition et application d'une taille fixe pour cette zone de
	 * l'interface, calculée en fonction du nombre de cellules et de la
	 * taille d'affichage.
	 */
	Dimension dim = new Dimension(TAILLE*CModele.LARGEUR+400,
				      TAILLE*CModele.HAUTEUR);
	this.setPreferredSize(dim);
	Controleur crtl = new Controleur(modele);
	this.addMouseListener( crtl);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se content de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() {
		if(!modele.isGameOver()) {

			repaint();
		}else{
			System.out.println("Game Over!");
		}
	}

    /**
     * Les éléments graphiques comme [JPanel] possèdent une méthode
     * [paintComponent] qui définit l'action à accomplir pour afficher cet
     * élément. On la redéfinit ici pour lui confier l'affichage des cellules.
     *
     * La classe [Graphics] regroupe les éléments de style sur le dessin,
     * comme la couleur actuelle.
     */
    public void paintComponent(Graphics g) {
		super.repaint();
		/** Pour chaque cellule... */
		for (int i = 1; i <= CModele.LARGEUR; i++) {
			for (int j = 1; j <= CModele.HAUTEUR; j++) {
				/**
				 * ... Appeler une fonction d'affichage auxiliaire.
				 * On lui fournit les informations de dessin [g] et les
				 * coordonnées du coin en haut à gauche.
				 */
				paint(g, modele.getCellule(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
				int nbJoueursCellule = modele.getCellule(i, j).getJoueurs().size();
				if (nbJoueursCellule == 0) {
					continue;
				} else if (nbJoueursCellule > 1) {
					//System.out.println("nb joueur "+ modele.getCellule(i, j).getJoueurs().size());
				}
				//System.out.println("nb joueur "+ modele.getCellule(i, j).getJoueurs().size());
				int maxJoueurs = modele.getMaxJoueurs();
				int tailleUnJoueur = TAILLE / maxJoueurs;
				assert maxJoueurs <= TAILLE;
				for (int k = 0; k < modele.getCellule(i, j).getJoueurs().size(); k++) {
					assert modele.getCellule(i, j).getJoueurs().size() <= TAILLE;
					Graphics2D g2 = (Graphics2D) g;

					int x = (i - 1) * TAILLE;
					int y = (j - 1) * TAILLE;
					int width = tailleUnJoueur * nbJoueursCellule;
					int height = tailleUnJoueur * nbJoueursCellule;
					g2.setColor(Color.RED);
					g2.fillRect(x, y, width, height);

					//System.out.println("la taille " + modele.getCellule(i, j).getJoueurs().size());
					// Draw the red rectangle

					//g2.draw(new Rectangle2D.Double(x, y, width, height));


				}

			}

		}
		for (int i = CModele.LARGEUR+1; i<CModele.LARGEUR+400;i++)
		{
			for (int j = 0; j < CModele.HAUTEUR+1; j++)
			{
				g.setColor(Color.black);
				g.fillRect((i - 1) * TAILLE, (j - 1) * TAILLE, TAILLE, TAILLE);

			}
		}
		int joueurLabelX =1000;
		int cleLabelX = 900;
		int actionRestantesLabelX = 800;
		int arctfactLabelX = 750;
		g.setFont(new Font("Purisa", Font.PLAIN, 20));
		g.setColor(Color.RED);
		g.drawString("Joueur " + modele.getIdJoueurCourant(), joueurLabelX, 200);

		g.setFont(new Font("Purisa", Font.PLAIN, 15));
		g.setColor(Color.BLUE);
		g.drawString("Actions Restantes  " + modele.getActionsRestantes(), actionRestantesLabelX, 400);

		g.setFont(new Font("Purisa", Font.PLAIN, 10));
		g.setColor(Color.WHITE);
		g.drawString("Cle :  ", cleLabelX, 180);
		ArrayList<Element> cles = modele.getCleJoueurCourant();
		ArrayList<Element> arctfacts = modele.getArctfactJoueurCourant();
		;
		//System.out.println("size Arctfact " + cles.size());
		int marge = 10;
		for (int i = 0; i < cles.size(); i++) {
			if (cles.get(i) == Element.AIR) {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Clé AIR ", cleLabelX, 200 + (i * marge));
			} else if (cles.get(i) == Element.EAU) {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Clé EAU", cleLabelX, 200 + (i * marge));
			} else if (cles.get(i) == Element.FEU) {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Clé FEU", cleLabelX, 200 + (i * marge));
			} else {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Clé TERRE", cleLabelX, 200 + (i * marge));
			}
		}

		g.setFont(new Font("Purisa", Font.PLAIN, 10));
		g.setColor(Color.white);
		g.drawString("Arctfact :  ", arctfactLabelX, 180);

		for (int i = 0; i < arctfacts.size(); i++) {
			if (arctfacts.get(i) == Element.AIR) {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Arctfact AIR ", arctfactLabelX, 200 + (i * marge));
			} else if (arctfacts.get(i) == Element.EAU) {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Arctfact EAU", arctfactLabelX, 200 + (i * marge));
			} else if (arctfacts.get(i) == Element.FEU) {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Arctfact FEU", arctfactLabelX, 200 + (i * marge));
			} else {
				g.setFont(new Font("Purisa", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Arctfact TERRE", arctfactLabelX, 200 + (i * marge));
			}

		}
		checkJeuGagnee(g);

	}

	public void checkJeuGagnee(Graphics g)
	{
		boolean arctfactsTrouvee =modele.ToutArctfactsTrouvees();
		boolean joueursDansLaMemeZone = modele.ToutJoueursZoneHeliport();
		Cellule cellule = modele.getCoordsHeliport();
		if(cellule instanceof Heliport)
		{
			System.out.println("Jeu Gagné!");
			g.setFont(new Font("Purisa", Font.PLAIN, 50));
			g.setColor(Color.blue);
			g.drawString("Gagné!", 250, 250 );
		}
	}

	public void checkJeuPerdu(Graphics g)
	{
		if(modele.isGameOver())
		{
			System.out.println("Partie Perdue");
			g.setFont(new Font("Purisa", Font.PLAIN, 50));
			g.setColor(Color.RED);
			g.drawString("Perdu", 250, 250);
		}
	}

    /**
     * Fonction auxiliaire de dessin d'une cellule.
     * Ici, la classe [Cellule] ne peut être désignée que par l'intermédiaire
     * de la classe [CModele] à laquelle elle est interne, d'où le type
     * [CModele.Cellule].
     * Ceci serait impossible si [Cellule] était déclarée privée dans [CModele].
     */
    private void paint(Graphics g, Cellule c, int x, int y) {

		/** Sélection d'une couleur. */
		switch (c.etat) {

			case NORMALE:
				g.setColor(c.getNormalEtatColor());
				break;
			case INONDEE:
				g.setColor(c.getInondeeEtatColor());
				break;
			case SUBMERGEE:
				g.setColor(c.getSubmergeeEtatColor());
				break;
		}
		g.fillRect(x, y, TAILLE, TAILLE);

	}

}

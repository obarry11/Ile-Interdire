package com.company;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

class Controleur implements ActionListener, KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(compteursActions <maxNbActions) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    System.out.println("up");
                    modele.joueurMoveUp(getJoueur(idJoueurActif));
                    System.out.println("id joueur actif "+ idJoueurActif);

                    ajouterActions();
                    break;
                case KeyEvent.VK_DOWN:
                    // handle down
                    System.out.println("down");
                    Joueur joueurTemp = getJoueur(idJoueurActif);
                    if(joueurTemp == null)
                    {
                        System.out.println("joueur avec ce id est nul");
                    }
                    System.out.println("id de joueur " + joueurTemp.getIdJoueur());
                    modele.joueurMoveDown(getJoueur(idJoueurActif));
                    System.out.println("id joueur actif "+ idJoueurActif);
                    ajouterActions();
                    break;
                case KeyEvent.VK_LEFT:
                    // handle left
                    System.out.println("left");
                    modele.joueurMoveLeft(getJoueur(idJoueurActif));
                    System.out.println("id joueur actif "+ idJoueurActif);
                    ajouterActions();
                    break;
                case KeyEvent.VK_RIGHT:
                    // handle right
                    System.out.println("Right");
                    modele.joueurMoveRight(getJoueur(idJoueurActif));
                    System.out.println("id joueur actif "+ idJoueurActif);
                    ajouterActions();
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("Space");
                    if(modele.assecherCellule(getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY()))
                    {
                        ajouterActions();

                    }else{
                        System.out.println("La cellule ne peut pas etre assécher" );
                    }

                    break;

                case KeyEvent.VK_ENTER:
                    System.out.println("Entree");
                    Cellule celluleJoueur = modele.getCellule(getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY());

                    // System.out.println("ARCTFACT TROUVEE");
                    Arctfact celluleArctfact = (Arctfact) celluleJoueur;
                    if(celluleArctfact instanceof Arctfact ) {
                        System.out.println("erreur");
                        // celluleArctfact.couleur[0] = Color.blue;
                        Element cleRetournee = getJoueur(idJoueurActif).possedeCle(celluleArctfact.element);
                        if(cleRetournee != null) {
                            System.out.println("cle avec element trouvee");
                            modele.removeCle(cleRetournee, getJoueur(idJoueurActif));
                            modele.addArctfactToJoueur(celluleArctfact.element,getJoueur(idJoueurActif));
                            modele.convertirArctfactToNormal(celluleArctfact);
                        }

                    }


                    break;

                case KeyEvent.VK_Z:

                    if(modele.checkHauteurBound(new Cellule(this.modele,getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY())))
                    {
                        return;
                    }


                    if (modele.assecherCellule(getJoueur(idJoueurActif).getX(), getJoueur(idJoueurActif).getY()-1))
                    {
                        ajouterActions();

                    } else {
                        System.out.println("La cellule ne peut pas etre assécher");
                    }

                    break;
                case KeyEvent.VK_S:

                    if(modele.checkHauteurBound(new Cellule(this.modele,getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY())))
                    {
                        return;
                    }


                    if (modele.assecherCellule(getJoueur(idJoueurActif).getX(), getJoueur(idJoueurActif).getY()+1))
                    {
                        ajouterActions();

                    } else {
                        System.out.println("La cellule ne peut pas etre assécher");
                    }

                    break;

                case KeyEvent.VK_Q:

                    if(modele.checkLargeurBound(new Cellule(this.modele,getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY())))
                    {
                        return;
                    }


                    if (modele.assecherCellule(getJoueur(idJoueurActif).getX()-1, getJoueur(idJoueurActif).getY()))
                    {
                        ajouterActions();

                    } else {
                        System.out.println("La cellule ne peut pas etre assécher");
                    }

                    break;
                case KeyEvent.VK_D:

                    if(modele.checkLargeurBound(new Cellule(this.modele,getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY())))
                    {
                        return;
                    }


                    if (modele.assecherCellule(getJoueur(idJoueurActif).getX()+1, getJoueur(idJoueurActif).getY()))
                    {
                        ajouterActions();

                    } else {
                        System.out.println("La cellule ne peut pas etre assécher");
                    }

                    break;

                case KeyEvent.VK_1:
                  if( modele.EchangeDeCles(getJoueur(idJoueurActif),0))
                  {
                      ajouterActions();
                  }
                    break;


                case KeyEvent.VK_2:
                    if( modele.EchangeDeCles(getJoueur(idJoueurActif),1))
                    {
                        ajouterActions();
                    }
                    break;


                case KeyEvent.VK_3:
                    if( modele.EchangeDeCles(getJoueur(idJoueurActif),2))
                    {
                        ajouterActions();
                    }
                    break;


                case KeyEvent.VK_4:
                    if( modele.EchangeDeCles(getJoueur(idJoueurActif),3))
                    {
                        ajouterActions();
                    }
                    break;

            }


            updateListJoueurs();
        }else{
            System.out.println("numéro d'actions maximum est atteint!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    /**
     * On garde un pointeur vers le modèle, car le contrôleur doit
     * provoquer un appel de méthode du modèle.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du modèle est inutile. On pourrait se contenter de
     * faire directement référence au modèle enregistré pour la classe
     * englobante [VueCommandes].
     */
    CModele modele;
    ArrayList<Joueur> joueurs = new ArrayList< Joueur>();
    int idJoueurActif = 1;
    int compteur= 0;
    int maxNbActions = 3;
    int compteursActions = 0;
    int nbJoueurs = 0;
    public Controleur(CModele modele) {
        this.modele = modele;
        updateListJoueurs();
       nbJoueurs = joueurs.size();
        incrementerJoueurId();
       System.out.println("taille " + joueurs.size());
       initialiseActions();

    }

    public void updateListJoueurs(){
        joueurs =  modele.getJoueursFromCellules();
    }
    public Joueur getJoueur(int id){
        updateListJoueurs();
        for (int i = 0; i<joueurs.size(); i++) {
           if (joueurs.get(i).getIdJoueur() == idJoueurActif) {
               return joueurs.get(i);
           }

        }
        return null;
    }


    /**
     * Action effectuée à réception d'un événement : appeler la
     * méthode [avance] du modèle.
     */
    public void actionPerformed(ActionEvent e)
	{
        int randomEventInt = 2; //Utility.getRandomNumber(1,3);
        switch (randomEventInt) {
            case 1:
                System.out.println("------Rien faire");
                break;
            case 2:
                int randomElementInt = Utility.getRandomNumber(0,3);
                switch (randomElementInt)
                {
                    case 0:
                        modele.addCle(Element.EAU, getJoueur(idJoueurActif));
                        System.out.println("------ajouter element EAU ");
                        break;
                    case 1:
                        modele.addCle(Element.AIR, getJoueur(idJoueurActif));
                        System.out.println("------ajouter element AIR ");
                        break;
                    case 2:
                        modele.addCle(Element.TERRE, getJoueur(idJoueurActif));
                        System.out.println("------ajouter element TERRE ");
                        break;
                    case 3:
                        modele.addCle(Element.FEU, getJoueur(idJoueurActif));
                        System.out.println("------ajouter element FEU ");
                        break;

                }
                break;
            case 3:
                modele.innodeeZone(getJoueur(idJoueurActif).getX(),getJoueur(idJoueurActif).getY());
                System.out.println("------innodee Zone Joueur ");
                break;
        }
        incrementerJoueurId();
        initialiseActions();
		modele.avance();
        System.out.println("Maintenant c'est le role du joueur : " +idJoueurActif);
        updateListJoueurs();


    }

    public void incrementerJoueurId(){
        idJoueurActif = ((compteur)% (nbJoueurs))+1;
        compteur++;
        modele.setIdJoueurCourant(idJoueurActif);

    }


   public void ajouterActions(){
        compteursActions++;
        modele.setActionsRestantes(maxNbActions-compteursActions);
        System.out.println("nombre d'actions restantes" + (maxNbActions-compteursActions));

    }
    public void initialiseActions(){
        compteursActions = 0;
        modele.setActionsRestantes(maxNbActions-compteursActions);
    }
}

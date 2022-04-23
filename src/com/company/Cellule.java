package com.company;


import java.awt.*;
import java.util.ArrayList;

enum ZoneEtat {
    NORMALE, INONDEE, SUBMERGEE
}

class Cellule {
    protected final int x;
    protected final int y;
     protected CModele modele;
     protected Color couleur[]= {Color.green, Color.cyan, Color.blue};
                                // index[0]: couleur de l'état normal
                                // index[1] : couleur de l'état inondé
                                //index[2] : couleur de l'état submergé

    protected ArrayList<Joueur> joueurs= new ArrayList<Joueur>();

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    public void creeJoueur(){
        joueurs.add(new Joueur(x,y));
    }
    public void prendCle(Element cle,Joueur joueur){
        for(int i = 0; i< joueurs.size(); i++)
        {
            if(joueurs.get(i) == joueur)
            {
                joueurs.get(i).PrendCle(cle);
            }
        }
    }
    public void removeCle(Element cle,Joueur joueur){
        for(int i = 0; i< joueurs.size(); i++) {
            if(joueurs.get(i) == joueur) {
                joueurs.get(i).removeCle(cle);
            }
        }
    }



    public void removeJoueur(Joueur joueur) {
        joueurs.remove(joueur);
    }
    public void ajouterJoueur(Joueur j){
     joueurs.add(new Joueur(j.getIdJoueur(),x,y, j.getCles(), j.getArctfactsTrouvee()));
    }

    public ZoneEtat getEtat() {
        return etat;
    }
    protected Color getNormalEtatColor(){
        return couleur[0];
    }

    protected Color getInondeeEtatColor(){
        return couleur[1];
    }

    protected Color getSubmergeeEtatColor(){
        return couleur[2];
    }

    protected ZoneEtat etat;




    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Cellule(CModele modele, int x, int y)
    {
        this.modele = modele;
        this.etat = ZoneEtat.NORMALE;
        this.x = x; this.y = y;

    }

    public Cellule(CModele modele, int x, int y,ArrayList<Joueur> joueurs)
    {
        this.modele = modele;
        this.etat = ZoneEtat.NORMALE;
        this.x = x; this.y = y;
        this.joueurs = joueurs;


    }

    @Override
    public String toString()
    {
        return "Cellule Normal";
    }
    public void Inondee()
    {
        this.etat = ZoneEtat.INONDEE;
       System.out.println("Cellule Inondee");
    }
    public void Submergee()
    {
        this.etat = ZoneEtat.SUBMERGEE;
        System.out.println("Submergee");
    }

    public boolean Assechee()
    {
        if(this.etat ==ZoneEtat.INONDEE ) {
          this.etat = ZoneEtat.NORMALE;
          return true;
        }else{
            return false;
        }

    }
    public  void addArctfact(Element arctfact, Joueur joueur){
        for(int i=0; i<joueurs.size();i++)
        {
            if(joueurs.get(i) == joueur)
            {
                joueurs.get(i).addArctfact(arctfact);
            }
        }

    }

    public void addCle(Element element, Joueur joueur){
        for(int i=0; i<joueurs.size();i++)
        {
            if(joueurs.get(i) == joueur)
            {
              joueurs.get(i).addCle(element);
            }
        }
    }

}

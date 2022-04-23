package com.company;

import javafx.scene.control.Cell;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

public class Joueur  {

    static int id = 0;

    public ArrayList<Element> getCles() {
        return cles;
    }


    private int idJoueur = 0;
    private ArrayList<Element> cles= new ArrayList<Element>();

    public ArrayList<Element> getArctfactsTrouvee() {
        return arctfactsTrouvee;
    }

    private ArrayList<Element> arctfactsTrouvee = new ArrayList<Element>();
    public int getX() {
        return x;
    }

    private int x;

    public int getY() {
        return y;
    }

    private int y =0;

    public Joueur(int i, int j) {
        x = i;
        y = j;
        id++;
        idJoueur = id;

    }
    public Joueur(int idJ, int i , int j, ArrayList<Element> cles,ArrayList<Element> arctfactsTrouvee){
        x = i;
        y = j;
        this.idJoueur = idJ;
        this.cles = cles;
        this.arctfactsTrouvee = arctfactsTrouvee;

    }

    public void addCle(Element element){
        cles.add(element);

    }

   public void PrendCle(Element cle){
        cles.add(cle);
   }

   public void removeCle(Element cle){
        cles.remove(cle);
   }
    public Element possedeCle(Element elementZone){
        for(int i = 0; i< cles.size();i++) {
           if(cles.get(i) == elementZone) {
               return cles.get(i);
           }

        }
        return null;
    }

    public void addArctfact(Element arctfact){
        arctfactsTrouvee.add(arctfact);
    }

    public static int getId() {
        return id;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    @Override
    public String toString()
    {
        return "Joueur " + id;
    }
}

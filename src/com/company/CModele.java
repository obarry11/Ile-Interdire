package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

class CModele extends Observable {
    public static final int HAUTEUR=40, LARGEUR=60;
	ArrayList<Pair<Integer,Integer>> artfactCoords = new ArrayList< Pair<Integer,Integer>>();
	//public static final int HAUTEUR=5, LARGEUR= 10;
	int hauteurHeliport = HAUTEUR/2;
	int largeurHeliport = LARGEUR/2;
	public boolean sacDeSable = false;
	ArrayList<Pair<Integer,Integer>> joeursCoords= new ArrayList< Pair<Integer,Integer>>();
	private Cellule heliport = new Heliport(this,hauteurHeliport, largeurHeliport);//L'heliport est au centre

	public boolean getSacDeSable(){return sacDeSable;}

	public void setSacDeSable(boolean sacDeSable) {
		this.sacDeSable = sacDeSable;
	}

	public int getMaxJoueurs() {
		return maxJoueurs;
	}

	private int maxJoueurs = 4;

	public int getIdJoueurCourant() {
		return idJoueurCourant;
	}

	public ArrayList<Element> getArctfactJoueurCourant() {
		Joueur joueur = getJoueurAvecId(idJoueurCourant);
		if (joueur != null) {
			ArrayList<Element> arctfacts = joueur.getArctfactsTrouvee();
			if (arctfacts != null) {
				return arctfacts;
			} else {
				System.out.println("Arctfact Null");
				return null;
			}
		}
		System.out.println("JOUEUR EST NUL!");
		return  null;
	}

	public ArrayList<Element> getCleJoueurCourant() {
		return getJoueurAvecId(idJoueurCourant).getCles();

	}


	public Joueur getJoueurAvecId( int idJoueurActif){
		ArrayList<Joueur> joueurs = getJoueursFromCellules();
		for (int i = 0; i<joueurs.size(); i++) {
			if (joueurs.get(i).getIdJoueur() == idJoueurActif) {
				return joueurs.get(i);
			}
		}
		return null;
	}

	public void setIdJoueurCourant(int idJoueurCourant) {
		this.idJoueurCourant = idJoueurCourant;
	}

	public void setActionsRestantes(int actionsRestantes) {
		this.actionsRestantes = actionsRestantes;
	}

	//private Cellule joueur = new Joueur(this,hauteurJoueur,largeurJoueur);
	int idJoueurCourant= 0;

	public int getActionsRestantes() {
		return actionsRestantes;
	}

	int actionsRestantes = 0;




	public void setGameOver(boolean gameOver) {
		isGameOver = gameOver;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	private boolean isGameOver = false;
    private Cellule[][] cellules;
	//ArrayList<Cellule> cellulesInondees = new ArrayList<Cellule>();
	//ArrayList< ArrayList<Cellule> > cellules = new ArrayList< ArrayList<Cellule> >();




    public CModele() {
		createArtfacts();
		createJoueurs();
		cellules = new Cellule[LARGEUR+2][HAUTEUR+2];

		for(int i=0; i<LARGEUR+2; i++) {
			for(int j=0; j<HAUTEUR+2; j++) {
				if( i == largeurHeliport && j ==hauteurHeliport) {
					cellules[i][j] = new Heliport(this, i, j);
				}
				else {
					boolean artfactTrouvee = false;
					for(int k =0; k<artfactCoords.size();k++)
					{
						if(i==artfactCoords.get(k).getKey() && j==artfactCoords.get(k).getValue())
						{
							cellules[i][j] = new Arctfact(this,i,j);
							artfactTrouvee = true;
							break;
						}
					}
					boolean joueurTrouvee = false;
					for(int k =0; k<joeursCoords.size();k++)
					{
						if(i==joeursCoords.get(k).getKey() && j==joeursCoords.get(k).getValue())
						{
							if(cellules[i][j] == null){
								cellules[i][j] = new Cellule(this,i,j);
							}

							//System.out.println("ajouté! "+ "at " + i+ " ,"+ j);
							joueurTrouvee = true;
						    cellules[i][j].creeJoueur();
						}
					}
					if(artfactTrouvee == false&& joueurTrouvee== false)
					{
						cellules[i][j] = new Cellule(this, i, j);
					}
				}
			}
		}

    }
	public boolean checkHauteurBound(Cellule cellule) {
		return cellule.getY() == 1 || cellule.getY()== HAUTEUR  ;
	}

	public boolean checkLargeurBound(Cellule cellule) {
		return cellule.getX() == 1 || cellule.getX()== LARGEUR  ;
	}

	public boolean isArctfactZone(Cellule cellule){
		if(cellule instanceof Arctfact){
			return true;
		}else {
			return false;
		}
	}


   public void addCle(Element element,Joueur joueur){
		cellules[joueur.getX()][joueur.getY()].addCle(element, joueur);
   }


	public void prendCle(Element cle,Joueur joueur,Cellule cellule){

		for(int i=0; i<LARGEUR+2; i++)
		{
			for(int j=0; j<HAUTEUR+2; j++)
			{
				cellules[i][j].prendCle(cle,joueur);
			}
		}

	}

   public void addArctfactToJoueur(Element arctfact,Joueur joueur){
	   cellules[joueur.getX()][joueur.getY()].addArctfact(arctfact,joueur);
   }


	public void removeCle(Element cle,Joueur joueur){
				cellules[joueur.getX()][joueur.getY()].removeCle(cle, joueur);
	}

	public void convertirArctfactToNormal(Cellule celluleArctfact){

				cellules[celluleArctfact.getX()][celluleArctfact.getY()] = new Cellule(this, celluleArctfact.getX(),celluleArctfact.getY(),celluleArctfact.getJoueurs());
    System.out.println("arctfact pris");
	}



	public void joueurMoveUp(Joueur joueur){
		Cellule celluleUp = getCelluleUp(cellules[joueur.getX()][joueur.getY()]);
		if(celluleUp == null){
			return;
		}

		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleUp.getX()][celluleUp.getY()].getX() + ",la j " + cellules[celluleUp.getX()][celluleUp.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleUp.getX()][celluleUp.getY()].getJoueurs().size());


	   cellules[celluleUp.getX()][celluleUp.getY()].ajouterJoueur(joueur);
	   cellules[joueur.getX()][joueur.getY()].removeJoueur(joueur);


		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleUp.getX()][celluleUp.getY()].getX() + ",la j " + cellules[celluleUp.getX()][celluleUp.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleUp.getX()][celluleUp.getY()].getJoueurs().size());
		System.out.println("==================================================");

	}
	public 	Cellule getCelluleUp(Cellule cellule){
		if(checkHauteurBound(cellule))
		{
			return null;
		}else {
		return new Cellule(this,cellule.getX(),cellule.getY()-1);
		}
	}


	public 	Cellule getCelluleDown(Cellule cellule){
		if  (checkHauteurBound(cellule))
		{
			return null;
		}else {
			return new Cellule(this,cellule.getX(),cellule.getY()+1);
		}
	}
	public void joueurMoveDown(Joueur joueur){
		Cellule celluleDown = getCelluleDown(cellules[joueur.getX()][joueur.getY()]);
		if(celluleDown == null){
			return;
		}

		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleDown.getX()][celluleDown.getY()].getX() + ",la j " + cellules[celluleDown.getX()][celluleDown.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleDown.getX()][celluleDown.getY()].getJoueurs().size());


		cellules[celluleDown.getX()][celluleDown.getY()].ajouterJoueur(joueur);
		cellules[joueur.getX()][joueur.getY()].removeJoueur(joueur);


		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleDown.getX()][celluleDown.getY()].getX() + ",la j " + cellules[celluleDown.getX()][celluleDown.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleDown.getX()][celluleDown.getY()].getJoueurs().size());
		System.out.println("==================================================");

	}

	public 	Cellule getCelluleRight(Cellule cellule){
		if(checkLargeurBound(cellule))
		{
			return null;
		}else {
			return new Cellule(this,cellule.getX()+1,cellule.getY());
		}
	}
	public void joueurMoveRight(Joueur joueur){
		Cellule celluleRight = getCelluleRight(cellules[joueur.getX()][joueur.getY()]);
		if(celluleRight == null){
			return;
		}


		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleRight.getX()][celluleRight.getY()].getX() + ",la j " + cellules[celluleRight.getX()][celluleRight.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleRight.getX()][celluleRight.getY()].getJoueurs().size());


		cellules[celluleRight.getX()][celluleRight.getY()].ajouterJoueur(joueur);
		cellules[joueur.getX()][joueur.getY()].removeJoueur(joueur);


		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleRight.getX()][celluleRight.getY()].getX() + ",la j " + cellules[celluleRight.getX()][celluleRight.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleRight.getX()][celluleRight.getY()].getJoueurs().size());
		System.out.println("==================================================");

	}

	public 	Cellule getCelluleLeft(Cellule cellule){
		if(checkLargeurBound(cellule) )
		{
			return null;
		}else {
			return new Cellule(this,cellule.getX()-1,cellule.getY());
		}
	}
	public void joueurMoveLeft(Joueur joueur){
		Cellule celluleLeft = getCelluleLeft(cellules[joueur.getX()][joueur.getY()]);
		if(celluleLeft == null){
			return;
		}


		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleLeft.getX()][celluleLeft.getY()].getX() + ",la j " + cellules[celluleLeft.getX()][celluleLeft.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleLeft.getX()][celluleLeft.getY()].getJoueurs().size());


		cellules[celluleLeft.getX()][celluleLeft.getY()].ajouterJoueur(joueur);
		cellules[joueur.getX()][joueur.getY()].removeJoueur(joueur);


		System.out.println("nous sommes dans la cellule i: "+ joueur.getX() + " ,la j "+ joueur.getY() );
		System.out.println("avec nombre de joueurs " + cellules[joueur.getX()][joueur.getY()].getJoueurs().size());
		System.out.println("Cellule Up i : "+cellules[celluleLeft.getX()][celluleLeft.getY()].getX() + ",la j " + cellules[celluleLeft.getX()][celluleLeft.getY()].getY());
		System.out.println("avec nombre de joueurs " + cellules[celluleLeft.getX()][celluleLeft.getY()].getJoueurs().size());
		System.out.println("==================================================");

	}




    public void avance() {
		System.out.println("avance");
		inondee3ZonesAleatoires();
		if(cellules[largeurHeliport][hauteurHeliport].getEtat() == ZoneEtat.SUBMERGEE){
		//cellules[largeurJoueur][hauteurJoueur].getEtat() == ZoneEtat.SUBMERGEE)
			setGameOver(true);
		}
		notifyObservers();
    }

	public Cellule getCellule(int ligne, int col) {
		return cellules[ligne][col];
	}

	public boolean assecherCellule(int x, int y) {
		if(cellules[x][y].Assechee()) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Joueur> getJoueursFromCellule(int ligne, int col) {
		return cellules[ligne][col].getJoueurs();

	}
	public ArrayList<Joueur> getJoueursFromCellules() {
		ArrayList<Joueur> toutLesJoueurs = new ArrayList<Joueur>();
		for(int i=0; i<LARGEUR+2; i++) {
			for(int j=0; j<HAUTEUR+2; j++) {
				toutLesJoueurs.addAll(cellules[i][j].getJoueurs());
			}
		}
		return  toutLesJoueurs;

	}

	public void inondee3ZonesAleatoires() {
    //Searching  for normal zones and adding them in an arrayList
		ArrayList< Cellule> cellulesNormalesEtInnodees = new ArrayList< Cellule >();
		for(int i=0; i<LARGEUR+2; i++) {
			for(int j=0; j<HAUTEUR+2; j++) {
				if(cellules[i][j].getEtat() == ZoneEtat.NORMALE || cellules[i][j].getEtat() == ZoneEtat.INONDEE) {
					cellulesNormalesEtInnodees.add(cellules[i][j]);
				}
			}
		}
		if(cellulesNormalesEtInnodees.size() >= 3) {
			for(int k = 0; k<3; k++)
			{
				//Generating random index from cellulesNormalesEtInnodees
				int randomNormalCelluleIndex = Utility.getRandomNumber(0, cellulesNormalesEtInnodees.size() - 1);
				Cellule randomCellule = cellulesNormalesEtInnodees.get(randomNormalCelluleIndex);
				//Inondee the normal random cellule
				if(cellules[randomCellule.getX()][randomCellule.getY()].getEtat() == ZoneEtat.NORMALE)
				{
					cellules[randomCellule.getX()][randomCellule.getY()].Inondee();
					//Remove the normal random cellule from normal cellules
					cellulesNormalesEtInnodees.remove(randomNormalCelluleIndex);
				}
				//Submergee la zone innodee de cellule aléatoire
				else if(cellules[randomCellule.getX()][randomCellule.getY()].getEtat() == ZoneEtat.INONDEE)
				{
					cellules[randomCellule.getX()][randomCellule.getY()].Submergee();
					cellulesNormalesEtInnodees.remove(randomNormalCelluleIndex);
				}

			}
		}else {
			if(cellulesNormalesEtInnodees.size() > 0) {
				if(cellules[cellulesNormalesEtInnodees.get(0).getX()][cellulesNormalesEtInnodees.get(0).getY()].getEtat() == ZoneEtat.NORMALE) {
					cellules[cellulesNormalesEtInnodees.get(0).getX()][cellulesNormalesEtInnodees.get(0).getY()].Inondee();
					cellulesNormalesEtInnodees.remove(0);
				}else if(cellules[cellulesNormalesEtInnodees.get(0).getX()][cellulesNormalesEtInnodees.get(0).getY()].getEtat() == ZoneEtat.INONDEE)
				{
					cellules[cellulesNormalesEtInnodees.get(0).getX()][cellulesNormalesEtInnodees.get(0).getY()].Submergee();
					cellulesNormalesEtInnodees.remove(0);
				}
			}else{
				setGameOver(true);
				System.out.println("No cellule left");
			}
		}
	}
	public void innodeeZone(int x, int y){
		cellules[x][y].Inondee();
		System.out.println("innondee zone");
	}
	public void createArtfacts(){
		artfactCoords.add(new Pair(5,5));
		artfactCoords.add(new Pair(10,10));
		artfactCoords.add(new Pair(7,14));
		artfactCoords.add(new Pair(25,25));
		artfactCoords.add(new Pair(30,30));

	}

	public void createJoueurs(){
		joeursCoords.add(new Pair(10,5));
		//joeursCoords.add(new Pair(10,5));
		joeursCoords.add(new Pair(10,5));
		joeursCoords.add(new Pair(10,5));
		//joeursCoords.add(new Pair(7,25));
		joeursCoords.add(new Pair(10,5));
	//	joeursCoords.add(new Pair(25,30));

	}

	public boolean ToutJoueursZoneHeliport() {
		ArrayList<Joueur> joueurs = getJoueursFromCellules();
		int xJoueur1 = joueurs.get(0).getX();
		int yJoueur1 = joueurs.get(0).getY();
		boolean dansLaMemeZone = true;
		for (int i=1; i<joueurs.size();i++)
		{
			if(xJoueur1 != joueurs.get(i).getX() && yJoueur1 != joueurs.get(i).getY()) {
				dansLaMemeZone =false;
			}
		}
		return dansLaMemeZone;
	}

	public boolean ToutArctfactsTrouvees() {
		boolean arctfactAirRecuperee = false;
		boolean arctfactEauRecuperee = false;
		boolean arctfactFeuRecuperee = false;
		boolean arctfactTerreRecuperee = false;
		ArrayList<Joueur> joueurs = getJoueursFromCellules();
		for(int i = 0; i < joueurs.size() ; i++) {
			ArrayList<Element> arctfacts = joueurs.get(i).getArctfactsTrouvee();
			for(int j = 0; j < arctfacts.size(); j++) {
				switch (arctfacts.get(i)) {
					case AIR:
						arctfactAirRecuperee = true;
						break;
					case EAU:
						arctfactEauRecuperee = true;
						break;
					case FEU:
						arctfactFeuRecuperee = true;
						break;
					case TERRE:
						arctfactTerreRecuperee = true;
						break;

				}
			}
		}
		return arctfactAirRecuperee && arctfactEauRecuperee && arctfactFeuRecuperee && arctfactTerreRecuperee;
	}

	public Cellule getCoordsHeliport()
	{
		return new Cellule(this,hauteurHeliport,largeurHeliport);
	}

	public void isJoueurSubmergee () {
		for(int i=0; i<LARGEUR+2; i++) {
			for(int j=0; j<HAUTEUR+2; j++) {
				if(cellules[i][j].getJoueurs().size() >=1 && cellules[i][j].getEtat() == ZoneEtat.SUBMERGEE) {
					setGameOver(true);
					return;
				}
			}

		}

	}


	public boolean EchangeDeCles(Joueur moi,int CleIndex) {
		if(cellules[moi.getX()][moi.getY()].getJoueurs().size() >= 2) {
			for(int i=0; i<cellules[moi.getX()][moi.getY()].getJoueurs().size();i++) {
				if(cellules[moi.getX()][moi.getY()].getJoueurs().get(i) != moi) {
					//Echange DE Clés
					if(moi.getCles().size() > 0 ) {
						Element cle = moi.getCles().get(CleIndex);
						if(cle != null) {
							cellules[moi.getX()][moi.getY()].getJoueurs().get(i).PrendCle(cle);
							moi.removeCle(cle);
							System.out.println("Echange de clé avec succès");
							return true;
						}
					}

				}
			}

		}
		return false;
	}

}


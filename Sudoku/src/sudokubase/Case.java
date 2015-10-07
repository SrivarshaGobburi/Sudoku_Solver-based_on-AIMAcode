package sudokubase;

//importation d'un truc du package aima import bin.aima.core.util.datastructure.XYLocation;


public class Case {
	private int chiffre;
	private Ensemble sonCarre;
	private Ensemble saLigne;
	private Ensemble saColonne;
	private boolean estModifiable;
	

    public Case()
    {
        chiffre = 0;
        sonCarre = null;
        saLigne = null;
        saColonne = null;
        estModifiable = true;
    }
    
    public void setModifiable(boolean modifiable)
    {
    	this.estModifiable = modifiable;
    }
    
    public boolean estModifiable(){
    	return estModifiable;
    }

    public void setChiffre(String dataString)
    {
    	int data = Integer.parseInt(dataString);
    	setChiffre(data);
    }

    public void setChiffre(int unchiffre)
    {
    	if(unchiffre >= 1 && unchiffre <=9 && estModifiable)
    	{
    		this.chiffre = unchiffre;
    	}
    }
    
    public boolean estOuverte()
    {
        if(chiffre == 0)
        {
            return true;
        } else {
            return false;
        }
    }
    
    public int getChiffre(){
    	return chiffre;
    }

    public Ensemble getCarre(){
    	return sonCarre;
    }
    
    public Ensemble getLigne(){
    	return saLigne;
    }

    public Ensemble getColonne(){
    	return saColonne;
    }


    public void setSonCarre(Ensemble sonCarre){
    	this.sonCarre=sonCarre;
    }
    
    public void setSaLigne(Ensemble saLigne){
    	this.saLigne=saLigne;
    }
    public void setSaColonne(Ensemble saColonne){
    	this.saColonne=saColonne;
    }
    

    
    public String toString(){
    	if(this.chiffre == 0)
    	{
    		return "-";
    	} else {
    		return "" + chiffre;
    	}
    }
}
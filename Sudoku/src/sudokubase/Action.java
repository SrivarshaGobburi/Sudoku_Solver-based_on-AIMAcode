package sudokubase;

public class Action {


	private int colonne;
    private int ligne;
    private int chiffre;
    
    public int getChiffre()
    {
        return chiffre;
    }
    
    public int getColonne()
    {
        return colonne;
    }
    
    public int getLigne()
    {
        return ligne;
    }
    
    public String toString()
    {
        return "ligne: " + ligne + " colonne: " + colonne + " chiffre: " + chiffre;
    }
    
}

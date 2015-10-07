
package sudokubase;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Sudoku {
	private Case[][] grilleDeCases = new Case[9][9];
    private Ensemble[] carres = new Ensemble[9];
    private Ensemble[] colonnes = new Ensemble[9];
    private Ensemble[] lignes = new Ensemble[9];


    public Sudoku()
    {
        initGrille();
        initCarres();
        initColonnes();
        initLignes();
    }

    
    public void initGrille()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                grilleDeCases[i][j] = new Case();
            }
        }
    }


    public void initCarres()
    {
        for(int i = 0; i < carres.length; i++)
        {
            carres[i] = new Ensemble();
        }
    }
    
    public void initColonnes()
    {
        for(int i = 0; i < colonnes.length; i++)
        {
            colonnes[i] = new Ensemble();
        }
    }
    
    public void initLignes()
    {
        for(int i = 0; i < lignes.length; i++)
        {
            lignes[i] = new Ensemble();
        }
    }
        
    public String toString()
    {
        String returnString = "    [0][1][2] | [3][4][5] | [6][7][8]\n" + 
                              "    =================================\n";
        for(int i = 0; i < 9; i++)
        {
            if(i%3 == 0 && i > 1)
            {
                returnString += "-----------------------------------\n";
            }
            
            for(int j = 0; j < 9; j++)
            {
                if(j%3 == 0 && j > 1)
                {
                    returnString += " | ";
                }
                if(j == 0)
                {
                    returnString += "[" + i + "] ";
                }
                
                returnString += " " + grilleDeCases[i][j].toString() + " ";
   
                
            }
            returnString += "\n\n";
        }
        return returnString;
    }
   
    public boolean ActionEstValide(Action m)
    {
    	// Check if space is editable
    	if(!grilleDeCases[m.getLigne()][m.getColonne()].estModifiable())
    	{
    		return false;
    	}
        
        return true;
    }

    public void faireAction(Action m)
    {
        grilleDeCases[m.getLigne()][m.getColonne()].setChiffre(m.getChiffre());
    }


    public boolean estFinit()
    {
        // check each support structure for duplicates
        for(int i = 0; i < 9; i++)
        {
            if(carres[i].aUnDouble())
            {
            	// System.err.println("Quadrant has duplicate.");
                return false;
            }
            if(colonnes[i].aUnDouble())
            {
            	// System.err.println("Vertical has duplicate.");
                return false;
            }
            if(lignes[i].aUnDouble())
            {
            	// System.err.println("Horizontal has duplicate.");
                return false;
            }
        }
        
        // check each tile to make sure there are no empty spaces
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(grilleDeCases[i][j].estOuverte())
                {
                	System.err.println("Case vide.");
                    return false;
                }
            }
        }
        
        return true;
    }

    public boolean estVide(int i, int j)
    {
        return grilleDeCases[i][j].estOuverte();
    }
    
    public Case getCase(int i, int j)
    {
    	return grilleDeCases[i][j];
    }

    public void ChargerSudoku()
    {
        // fill the tile grid
    	String filePath = "./in/100sudoku.txt";
    	 
    	Scanner scanner;
		try {
			scanner = new Scanner(new File(filePath));
			// On boucle sur chaque champ detecté
	    	scanner.hasNextLine();
	    	    String line = scanner.nextLine();
	    	 
	    	    System.out.println(line);
	    		//faites ici votre traitement
	    	    
	    	    char[] charArray = line.toCharArray();
	    	    int [] leschiffres=new int[81];
	    	    for (int i=0 ;i<leschiffres.length;i++) {
					leschiffres[i]=Character.getNumericValue(charArray[i]);
				}
	    	    int k=0;
	    	    for(int i=0; i < lignes.length;i++){
	    	    	for(int j = 0; j < colonnes.length; j++)
	    	    	{
	    	    		
	    	    			grilleDeCases[i][j].setChiffre(leschiffres[k]);
	    	    			if(grilleDeCases[i][j].getChiffre()!=0){
	    	    			grilleDeCases[i][j].setModifiable(false);
	    	    			}
	    	    			k++;
	    	    	}
	    	   }
	    	    
	    	    
	    	 scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        // populate the supporting architecture
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                
                grilleDeCases[i][j].setSaLigne(lignes[i]);
                grilleDeCases[i][j].setSaColonne(colonnes[j]);
                int whichTile = i%3*3 + j%3;
                lignes[i].setCase(j, grilleDeCases[i][j]);
                colonnes[j].setCase(i, grilleDeCases[i][j]);
                if(i < 3)
                {
                    if(j < 3)
                    {
                        carres[0].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[0]);
                    } else if(j < 6) {
                        carres[1].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[1]);
                    } else {
                        carres[2].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[2]);
                    }
                } else if(i < 6){
                    if(j < 3)
                    {
                        carres[3].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[3]);
                    } else if(j < 6) {
                    	carres[4].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[4]);
                    } else {
                    	carres[5].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[5]);
                    }
                } else {
                    if(j < 3)
                    {
                    	carres[6].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[6]);
                    } else if(j < 6) {
                    	carres[7].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[7]);
                    } else {
                    	carres[8].setCase(whichTile, grilleDeCases[i][j]);
                        grilleDeCases[i][j].setSonCarre(carres[8]);
                    }
                }
            }
        }
    }


}

package sudokubase;

public class Ensemble {
	private Case[] cases = new Case[9];

    public void setCase(int i, Case newCase)
    {
    	cases[i] = newCase;
    }

    public Case[] getCases()
    {
    	return cases;
    }

    public boolean aUnDouble()
    {
        int[] duplicates = new int[9];
        for(int i = 0; i < cases.length; i++)
        {
            Case c = cases[i];
            if(c.getChiffre() != 0)
            {
                int chiffre = c.getChiffre();
                if(duplicates[chiffre-1] != 0)
                {
                    return true;
                } else {
                    duplicates[chiffre-1] = chiffre;
                }
            }
            
        }
        return false;
    }

    public boolean aUnDouble(Action tempAction)
    {
        int[] duplicates = new int[9];
        duplicates[tempAction.getChiffre()-1] = 1;
        
        for(int i = 0; i < cases.length; i++)
        {
            Case c = cases[i];
            if(c.getChiffre() != 0)
            {
                int data = c.getChiffre();
                if(duplicates[data-1] != 0)
                {
                    return true;
                } else {
                    duplicates[data-1] = data;
                }
            }
            
        }
        return false;
    }
    
}

    

package model;

import model.contract.Grille;

public class GrilleImpl implements Grille
{
	private CaseImpl[][] cases;
	
	public GrilleImpl(CaseImpl[][] cases)
	{
		super();
		this.cases=cases;
	}
	
	public GrilleImpl()
	{
		super();
		this.cases = new CaseImpl[9][9];
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				cases[i][j] = new CaseImpl(0,i,j);
			}
		}
	}

	public CaseImpl[][] getCases() {
		return cases;
	}

	public void setCases(CaseImpl[][] cases) {
		this.cases = cases;
	}

	@Override
	public CaseImpl getCase(int x, int y)
	{
		return cases[x][y];
	}
	
	public void affichage()
	{
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				if(j==8)
				{
					System.out.print(cases[i][j].getValue()+"\n");
				}
				else
				{
					System.out.print(cases[i][j].getValue()+" ");
				}
				
			}
		}
	}
}

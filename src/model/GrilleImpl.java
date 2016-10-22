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
}

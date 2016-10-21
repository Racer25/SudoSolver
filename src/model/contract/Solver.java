package model.contract;

import java.util.List;

import model.CaseImpl;

public interface Solver 
{
	public void solve();
	
	public List<CaseImpl> getCasesLigne(CaseImpl maCase);
	public List<CaseImpl> getCasesColonne(CaseImpl maCase);
	public List<CaseImpl> getCasesBlocRestantes(CaseImpl maCase);
}

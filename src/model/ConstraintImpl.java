package model;

public class ConstraintImpl 
{
	private CaseImpl case1;
	private CaseImpl case2;
	
	public ConstraintImpl(CaseImpl case1, CaseImpl case2)
	{
		this.case1=case1;
		this.case2=case2;
	}
	
	public boolean isSatisfied()
	{
		return case1.getValue()!=case2.getValue();
	}
	
	//Getters and setters
	public CaseImpl getCase1() {
		return case1;
	}

	public void setCase1(CaseImpl case1) {
		this.case1 = case1;
	}

	public CaseImpl getCase2() {
		return case2;
	}

	public void setCase2(CaseImpl case2) {
		this.case2 = case2;
	}

}

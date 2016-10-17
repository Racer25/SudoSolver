package model;


public class GrilleImpl
{
	private CaseImpl[] cases;
	
	public GrilleImpl(CaseImpl[] cases)
	{
		super();
		this.cases=cases;
	}

	public CaseImpl[] getCases() {
		return cases;
	}

	public void setCases(CaseImpl[] cases) {
		this.cases = cases;
	}
}

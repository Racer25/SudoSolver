package controller;

import java.util.Observable;
import java.util.Observer;

import model.CaseImpl;
import view.CaseViewImpl;

public class CaseController implements Observer
{
	private CaseImpl maCase;
	private CaseViewImpl caseView;
	
	public CaseController(CaseImpl maCase, CaseViewImpl caseView) 
	{
		this.maCase = maCase;
		this.caseView = caseView;
		
		maCase.addObserver(this);
	}
	

	@Override
	public void update(Observable o, Object arg) 
	{
		int value=(int) arg;
		if(value!=0)
		{
			caseView.getValueView().setText(Integer.toString(value));
		}
		else
		{
			caseView.getValueView().setText(" ");
		}
		//caseView.revalidate();
	}

}

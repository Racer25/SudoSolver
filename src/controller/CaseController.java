package controller;

import java.util.Observable;
import java.util.Observer;

import model.CaseImpl;
import view.CaseViewImpl;

public class CaseController implements Observer
{
	private CaseImpl maCase;
	private CaseViewImpl caseView;
	private int visuel;
	
	public CaseController(CaseImpl maCase, CaseViewImpl caseView, int visuel) 
	{
		this.maCase = maCase;
		this.caseView = caseView;
		this.visuel=visuel;
		
		maCase.addObserver(this);
	}
	

	@Override
	public void update(Observable o, Object arg) 
	{
		int value=(int) arg;
		if(this.visuel==0)
		{
			if(value!=0)
			{
				this.caseView.getValueView().setText(Integer.toString(value));
			}
			else
			{
				this.caseView.getValueView().setText(" ");
			}
		}
	}

}

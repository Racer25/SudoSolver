package controller;

//Imports
import java.util.Observable;
import java.util.Observer;
import model.CaseImpl;
import view.CaseViewImpl;
import view.WindowImpl;

public class CaseController implements Observer
{
	//Variables
	@SuppressWarnings("unused")
	private CaseImpl maCase;
	private CaseViewImpl caseView;
	private WindowImpl frame;
	
	public CaseController(CaseImpl maCase, CaseViewImpl caseView, WindowImpl frame) 
	{
		this.maCase = maCase;
		this.caseView = caseView;
		this.frame=frame;
		
		maCase.addObserver(this);
	}
	
	public void update(Observable o, Object arg) 
	{
		int value=(int) arg;
		if(this.frame.getVisuel()==0)
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

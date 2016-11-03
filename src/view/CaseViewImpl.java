package view;

//Imports
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.CaseImpl;

public class CaseViewImpl extends JPanel
{
	private static final long serialVersionUID = 8307207484767445082L;
	
	//Variables
	private CaseImpl caseImpl;
	private JLabel valueView;
	
	//Constructeur
	public CaseViewImpl(CaseImpl caseImpl)
	{
		this.caseImpl = caseImpl;
		if(caseImpl.getValue() != 0)
		{
			this.valueView = new JLabel(Integer.toString(caseImpl.getValue()));
		}
		else
		{
			this.valueView = new JLabel(" ");
		}

		this.add(valueView);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));	
	}
	
	public JLabel getValueView() 
	{
		return valueView;
	}
	
	public void setValueView(JLabel valueView) 
	{
		this.valueView = valueView;
	}

	public int getCaseImplValue() 
	{
		return caseImpl.getValue();
	}

	public void setCaseImplValue(int value) 
	{
		this.caseImpl.setValue(value);
	}
	
}

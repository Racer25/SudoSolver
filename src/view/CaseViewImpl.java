package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CaseViewImpl extends JPanel
{
	private static final long serialVersionUID = 8307207484767445082L;
	
	//Variables
	private Integer value;
	private JLabel valueView;
	
	//Constructeur
	public CaseViewImpl(Integer value){
		this.value = value;
		if(value != null){
			this.valueView = new JLabel(Integer.toString(value));
			this.add(valueView);
		}
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
	}
	
	public JLabel getValueView() {
		return valueView;
	}
	public void setValueView(JLabel valueView) {
		this.valueView = valueView;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}

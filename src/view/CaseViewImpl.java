package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CaseViewImpl extends JPanel
{
	private static final long serialVersionUID = 8307207484767445082L;
	private JLabel valueView;
	
	public JLabel getValueView() {
		return valueView;
	}
	public void setValueView(JLabel valueView) {
		this.valueView = valueView;
	}
	
}

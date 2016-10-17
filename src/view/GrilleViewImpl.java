package view;

import javax.swing.JPanel;

public class GrilleViewImpl extends JPanel
{
	private static final long serialVersionUID = 8573209872828408779L;
	
	private CaseViewImpl[] caseViews;

	public CaseViewImpl[] getCaseViews() {
		return caseViews;
	}

	public void setCaseViews(CaseViewImpl[] caseViews) {
		this.caseViews = caseViews;
	}

}

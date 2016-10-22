package view;

//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.CaseImpl;
import model.GrilleImpl;

public class GrilleViewImpl extends JPanel
{
	private static final long serialVersionUID = 8573209872828408779L;
	
	//Variables
	private GridLayout layoutGrille;
	private CaseImpl[][] tabCaseImpl;
	private CaseViewImpl[][] tabCaseViewImpl;
	
	private JPanel grille11;
	private JPanel grille12;
	private JPanel grille13;
	private JPanel grille21;
	private JPanel grille22;
	private JPanel grille23;
	private JPanel grille31;
	private JPanel grille32;
	private JPanel grille33;
	
	private GrilleImpl grilleImpl;
	
	//Constructeur
	public GrilleViewImpl(GrilleImpl grilleImpl){
		
		this.grilleImpl = grilleImpl;
		this.tabCaseImpl = grilleImpl.getCases();
		this.tabCaseViewImpl = new CaseViewImpl[9][9];
		layoutGrille = new GridLayout(3,3);
		
		for(int i = 0 ; i < 9 ; i++){
			for(int j = 0 ; j < 9 ; j++){
				tabCaseViewImpl[i][j] = new CaseViewImpl(tabCaseImpl[i][j]);
			}
		}
		
		grille11 = new JPanel();
		grille11.setLayout(layoutGrille);
		grille11.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille11.setBackground(Color.WHITE);
		
		grille12 = new JPanel();
		grille12.setLayout(layoutGrille);
		grille12.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille12.setBackground(Color.WHITE);
		
		grille13 = new JPanel();
		grille13.setLayout(layoutGrille);
		grille13.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille13.setBackground(Color.WHITE);
		
		grille21 = new JPanel();
		grille21.setLayout(layoutGrille);
		grille21.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille21.setBackground(Color.WHITE);
		
		grille22 = new JPanel();
		grille22.setLayout(layoutGrille);
		grille22.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille22.setBackground(Color.WHITE);
		
		grille23 = new JPanel();
		grille23.setLayout(layoutGrille);
		grille23.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille23.setBackground(Color.WHITE);
		
		grille31 = new JPanel();
		grille31.setLayout(layoutGrille);
		grille31.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille31.setBackground(Color.WHITE);
		
		grille32 = new JPanel();
		grille32.setLayout(layoutGrille);
		grille32.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille32.setBackground(Color.WHITE);
		
		grille33 = new JPanel();
		grille33.setLayout(layoutGrille);
		grille33.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		grille33.setBackground(Color.WHITE);
		
		this.add(grille11);
		this.add(grille21);
		this.add(grille31);
		this.add(grille12);
		this.add(grille22);
		this.add(grille32);
		this.add(grille13);
		this.add(grille23);
		this.add(grille33);
		
		this.setPreferredSize(new Dimension(200, 200));
		this.setLayout(layoutGrille);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		for(int i = 0 ; i < 9 ; i++){
			for(int j = 0 ; j < 9 ; j++){
				if(i<3 && j<3){
					grille11.add(tabCaseViewImpl[i][j]);
				}else if(i<6 && j<3){
					grille12.add(tabCaseViewImpl[i][j]);
				}else if(j<3){
					grille13.add(tabCaseViewImpl[i][j]);
				}else if(i<3 && j<6){
					grille21.add(tabCaseViewImpl[i][j]);
				}else if(i<6 && j<6){
					grille22.add(tabCaseViewImpl[i][j]);
				}else if(j<6){
					grille23.add(tabCaseViewImpl[i][j]);
				}else if(i<3){
					grille31.add(tabCaseViewImpl[i][j]);
				}else if(i<6){
					grille32.add(tabCaseViewImpl[i][j]);
				}else{
					grille33.add(tabCaseViewImpl[i][j]);
				}
			}
		}		
		
	}

	public CaseViewImpl[][] getCaseViews() {
		return tabCaseViewImpl;
	}

	public void setCaseViews(CaseViewImpl[][] caseViews) {
		this.tabCaseViewImpl = caseViews;
	}

	public GrilleImpl getGrilleImpl() {
		return grilleImpl;
	}

	public void setGrilleImpl(GrilleImpl grilleImpl) {
		this.grilleImpl = grilleImpl;
	}

}

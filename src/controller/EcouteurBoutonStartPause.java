package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.SolverImpl;
import model.contract.Solver;

public class EcouteurBoutonStartPause implements ActionListener{

	//Variables
	private ImageIcon imageBoutonStartPause;
	private JButton boutonStartPause; 
	private Solver solver;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		boutonStartPause = (JButton) arg0.getSource();
		imageBoutonStartPause = new ImageIcon("./images/pause.png" );
		boutonStartPause.setIcon(imageBoutonStartPause);
		
		//Résolution
		this.solver.solve();
	}

	public Solver getSolver() {
		return solver;
	}

	public void setSolver(Solver solver) {
		this.solver = solver;
	}

}

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EcouteurSaisie implements KeyListener{
	
	//Variables 
	private JTextField jTextField;
	private JTextField[][] tabJTextField;
	private int i;
	private int j;
	private JLabel message;
	private JPanel panelMessage;
	
	public EcouteurSaisie(JTextField[][] tabJTextField,int i,int j,JLabel message,JPanel panelMessage){
		this.i = i;
		this.j = j;
		this.tabJTextField = tabJTextField;
		this.jTextField = tabJTextField[i][j];
		this.message = message;
		this.panelMessage = panelMessage;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if((e.getKeyCode()==e.VK_1 && e.getKeyCode()<=e.VK_9)||(e.getKeyCode()>=e.VK_NUMPAD1 && e.getKeyCode()<=e.VK_NUMPAD9)){
			
			for(int x = 0 ; x < 9 ; x++){
				if(x!=j){
					if(tabJTextField[i][x].getText().equals(Character.toString(e.getKeyChar()))){
						e.consume();
					}
				}
			}
			for(int y = 0 ; y < 9 ; y++){
				if(y!=i){
					if(tabJTextField[y][j].getText().equals(Character.toString(e.getKeyChar()))){
						e.consume();
					}
				}
			}
			for(int z = i-i%3 ; z < i-i%3+3 ; z++){
				for(int a = j-j%3 ; a < j-j%3+3 ; a++){
					if(z!=i && a!=j){
						if(tabJTextField[z][a].getText().equals(Character.toString(e.getKeyChar()))){
							e.consume();
						}
					}
				}
			}
			if(!e.isConsumed()){
				jTextField.setText(Character.toString(e.getKeyChar()));
				message.setText("");
			}
			else{
				message.setText("Vos chiffres doivent être cohérents !");
			}
		}
		else if(e.getKeyCode()<=e.VK_DELETE){
			jTextField.setText("");
			message.setText("Vous ne pouvez entrer que des chiffres !");
			//panelMessage.revalidate();
		}
		else{
			e.consume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		e.consume();
		
	}

}

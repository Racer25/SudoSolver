package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class EcouteurSaisie implements KeyListener{
	
	//Variables 
	private JTextField jTextField;
	
	public EcouteurSaisie(JTextField jTextField){
		this.jTextField = jTextField;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if((e.getKeyCode()==e.VK_1 && e.getKeyCode()<=e.VK_9)||(e.getKeyCode()>=e.VK_NUMPAD1 && e.getKeyCode()<=e.VK_NUMPAD9)){
			jTextField.setText(Character.toString(e.getKeyChar()));
		}
		else if(e.getKeyCode()<=e.VK_DELETE){
			jTextField.setText("");
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

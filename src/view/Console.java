package view;

//Imports
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class Console extends OutputStream
{
	//Variables
    private JTextArea textArea;

    public Console(JTextArea textArea) 
    {
        this.textArea = textArea;
    }

    public void write(int b) throws IOException 
    {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.update(textArea.getGraphics());
    }
}
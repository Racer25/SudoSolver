package app;

import model.SsReaderImpl;

public class MainClass 
{
	public static void main(String[] args) 
	{
		SsReaderImpl r = new SsReaderImpl();
		r.lireSs("327146929-v3-29-L1.ss");
	}
}

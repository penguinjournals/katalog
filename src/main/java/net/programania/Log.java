package net.programania;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Log 
{   
	List<String> logBuffer = new ArrayList<String>();
	private static final String FILENAME = "output.log";
	
    public void add( String line )
    {
    	logBuffer.add(line);
    }
	public void persist() {
		try {
			FileWriter file = new FileWriter(FILENAME, true);
			for (int line = 0; line < logBuffer.size(); line++) {
				file.write(logBuffer.get(line) + "\n");
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't write to file");
			e.printStackTrace();
		}
	}
}

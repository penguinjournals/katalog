package net.programania;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Log
{   
	private List<String> logBuffer = new ArrayList<>();
	private static final String FILENAME = "output.log";
	
    void addLine(String line)
    {
    	logBuffer.add(line);
    }
	void persist() {
		try {
			FileWriter file = new FileWriter(FILENAME, true);
			for (String aLogBuffer : logBuffer) {
				file.write(aLogBuffer + "\n");
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't write to file");
			e.printStackTrace();
		}
	}
}

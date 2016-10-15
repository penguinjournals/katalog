package net.programania;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileDriver implements LogDriver{

	private static final String FILENAME = "output.log";

	public void persist(List<String> logBuffer) {
		for (String aLogBuffer : logBuffer) {
			System.out.println(aLogBuffer);
		}
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

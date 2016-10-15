package net.programania;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Log
{   
	private List<String> logBuffer = new ArrayList<>();
	
    void addLine(String line)
    {
    	logBuffer.add(line);
    }
	void persist() {
		LogFileWriter logFileWriter = new LogFileWriter();
		logFileWriter.persist(logBuffer);
		LogMailWriter logMailWriter = new LogMailWriter();
		logMailWriter.persist(logBuffer);
	}
}

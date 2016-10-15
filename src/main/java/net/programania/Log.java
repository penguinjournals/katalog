package net.programania;

import java.util.ArrayList;
import java.util.List;

class Log
{   
	private List<String> logBuffer = new ArrayList<>();
	private LogDriver logDriver;
	
	public Log(LogDriver logDriver){
		this.logDriver = logDriver;
	}
	
    void addLine(String line)
    {
    	logBuffer.add(line);
    }
	void persist() {
		logDriver.persist(logBuffer);
	}
}

package net.programania;

import java.util.ArrayList;
import java.util.List;

public class Log 
{   
	List<String> logBuffer = new ArrayList<String>();
    public void add( String line )
    {
    	logBuffer.add(line);
    }
	public void persist() {
		for (int line = 0; line < logBuffer.size(); line++) {
			System.out.println(logBuffer.get(line));
		}
	}
}

package net.programania;

import java.util.ArrayList;
import java.util.List;

public abstract class Log {
	
	List<String> logBuffer = new ArrayList<String>();
    public void addLine( String line )
    {
    	logBuffer.add(line);
    }
    
	public abstract void persist();
}

package net.programania;

import java.util.List;

public class InMemoryDriver implements LogDriver {

	@Override
	public void persist(List<String> logBuffer) {
		// TODO Auto-generated method stub
		// Triliri, nothing to be done
	}
	
	public String retrieveLog(List<String> logBuffer){
		String logContent = new String();
		for (String aLogBuffer : logBuffer) {
			logContent = logContent + aLogBuffer;
		}
		return logContent;
	}

}

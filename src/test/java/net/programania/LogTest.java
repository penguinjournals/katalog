package net.programania;

import java.io.IOException;

import junit.framework.TestCase;

public class LogTest 
    extends TestCase
{
	public void testLog() throws IOException
    {
		InMemoryDriver logDriver = new InMemoryDriver();
    	Log log = new Log(logDriver);
    	String expectedLog = "boniclo";
    	log.addLine(expectedLog);
    	log.persist();
    	String getLog = log.retrieveLog();
    	assertEquals(expectedLog, getLog);
    }
}

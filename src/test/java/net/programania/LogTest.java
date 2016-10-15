package net.programania;

import java.io.IOException;

import junit.framework.TestCase;

public class LogTest 
    extends TestCase
{
	public void testLog() throws IOException
    {
		FileDriver logDriver = null;
    	Log log = new Log(logDriver);
    	log.addLine("boniclo");
    	log.persist();
    }
	/*
	public void testMailLog()
    {
    	MailDriver logDriver = null;
    	Log log = new Log(logDriver);
    	log.addLine("boniclo");
    	log.persist();
    }
    */
}

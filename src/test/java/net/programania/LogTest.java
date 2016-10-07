package net.programania;

import junit.framework.TestCase;

public class LogTest 
    extends TestCase
{
	public void testLog()
    {
    	FileLog log = new FileLog();
    	log.addLine("boniclo");
    	log.addLine("piticli");
    	log.persist();
    }
	
	public void testMailLog()
    {
    	MailLog log = new MailLog();
    	log.addLine("boniclo");
    	log.addLine("piticli");
    	log.persist();
    }	
}

package net.programania;

import junit.framework.TestCase;

public class LogTest 
    extends TestCase
{
	public void testAddLine()
    {
    	Log log = new Log();
    	log.add("boniclo");
    	log.add("piticli");
    	log.persist();
    }
}

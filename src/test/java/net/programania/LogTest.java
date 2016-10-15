package net.programania;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

public class LogTest 
    extends TestCase
{
	public void testLog() throws IOException
    {
    	Log log = new Log();
    	log.addLine("boniclo");
    	log.persist();
    	try {
    		File logfile = new File("output.log");
			FileReader logfileReader = new FileReader(logfile);
			BufferedReader reader = new BufferedReader(logfileReader);
			String line = null;
			String current;
			while ((current = reader.readLine()) != null){
				line = current;
			}
			reader.close();
			assertEquals("boniclo", line);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/*
	public void testMailLog()
    {
    	MailLog log = new MailLog();
    	log.addLine("boniclo");
    	log.addLine("piticli");
    	log.persist();
    }
    */	
}

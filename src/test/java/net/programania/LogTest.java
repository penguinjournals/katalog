package net.programania;

import junit.framework.TestCase;

import java.io.IOException;

import org.mockito.Mockito;

public class LogTest extends TestCase {
  public void testLog() throws IOException {
	LogDriver logDriver = Mockito.mock(LogDriver.class);
    Log log = new Log(logDriver);
    String expectedLog = "boniclo";
    log.addLine(expectedLog);
    log.persist();
    Mockito.verify(logDriver,Mockito.times(1)).persist(Mockito.any());
  }
}

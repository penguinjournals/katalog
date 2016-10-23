package net.programania;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;

public class LogTest extends TestCase {
  public void testLog() throws IOException {
	LogDriver logDriver = Mockito.mock(LogDriver.class);
    Log log = new Log(logDriver);
    String expectedLog = "boniclo";
    log.addLine(expectedLog);
    log.persist();
    List<String> logBuffer = new ArrayList<>();
    logBuffer.add(expectedLog);
    Mockito.verify(logDriver,Mockito.times(1)).persist(logBuffer);
  }
}

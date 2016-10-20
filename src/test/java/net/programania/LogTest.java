package net.programania;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class LogTest extends TestCase {
  public void testLog() throws IOException {
    InMemoryDriver logDriver = new InMemoryDriver();
    Log log = new Log(logDriver);
    String expectedLog = "boniclo";
    log.addLine(expectedLog);
    log.persist();
    List<String> retrievedLog = logDriver.retrieveLog();

    assertEquals(retrievedLog.size(), 1);
    assertEquals(expectedLog, retrievedLog.get(0));
  }
}

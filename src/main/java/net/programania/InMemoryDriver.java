package net.programania;

import java.util.ArrayList;
import java.util.List;

class InMemoryDriver implements LogDriver {

  private final List<String> logBuffer = new ArrayList<>();

  @Override
  public void persist(List<String> logBuffer) {
    this.logBuffer.addAll(logBuffer);
  }

  List<String> retrieveLog() {
    return logBuffer;
  }
}
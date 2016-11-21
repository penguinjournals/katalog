package net.programania;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestingCliTest {


  @Test
  public void a_command() throws Exception {


    InMemoryDriver logger = new InMemoryDriver();

    GitAnalyzerCommand command = GitAnalyzerCommand
            .CommandFactory
            .with(logger)
            .getCommand(new String[]{"-one", "uno"});

    command.execute();

    assertTrue(command instanceof GitAnalyzerCommand.CommandFactory.OneCommand);

    assertTrue(logger.retrieveLog().get(0).contains("One"));
    assertTrue(logger.retrieveLog().get(0).contains("uno"));

  }

  @Test
  public void another_command() throws Exception {

    InMemoryDriver logger = new InMemoryDriver();

    GitAnalyzerCommand command = GitAnalyzerCommand
            .CommandFactory
            .with(logger)
            .getCommand(new String[]{"-another", "dos"});

    command.execute();

    assertTrue(command instanceof GitAnalyzerCommand.CommandFactory.AnotherCommand);

    assertTrue(logger.retrieveLog().get(0).contains("Another"));
    assertTrue(logger.retrieveLog().get(0).contains("dos"));

  }

}
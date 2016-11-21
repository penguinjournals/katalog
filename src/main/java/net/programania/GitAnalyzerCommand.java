package net.programania;

import static java.util.Collections.singletonList;

interface GitAnalyzerCommand {

  void execute();

  final class CommandFactory {

    private final LogDriver logger;

    CommandFactory(LogDriver logger) {
      this.logger = logger;
    }

    static CommandFactory with(LogDriver logger) {
      return new CommandFactory(logger);
    }

    GitAnalyzerCommand getCommand(String[] args) {
      String commandName = args[0];
      if ("-one".equals(commandName))
        return new OneCommand(args[1], logger);
      if ("-another".equals(commandName))
        return new AnotherCommand(args[1], logger);

      throw new RuntimeException("No existe comando para " + commandName);
    }

    class AnotherCommand implements GitAnalyzerCommand {
      private final String parametro;
      private final LogDriver logger;

      AnotherCommand(String parametro, LogDriver logger) {
        this.parametro = parametro;
        this.logger = logger;
      }

      @Override
      public void execute() {
        this.logger.persist(singletonList("soy Another commando y loggeo" + parametro));
      }
    }

    class OneCommand implements GitAnalyzerCommand {
      private final String parametro;
      private final LogDriver logger;

      OneCommand(String parametro, LogDriver logger) {
        this.parametro = parametro;
        this.logger = logger;
      }

      @Override
      public void execute() {
        this.logger.persist(singletonList("soy One commando y loggeo" + parametro));
      }
    }
  }
}
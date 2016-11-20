package net.programania;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentParser {
	CommandLine cmd;

	public ArgumentParser(String[] args) {
		Options options = new Options();
		Option repoPath = Option.builder("repo").required(true).hasArg(true).longOpt("repoPath").desc("Path to local working copy").build();
		Option masterBranch = Option.builder("master").required(false).hasArg(true).longOpt("masterBranchName").desc("Name of the branch which is in production").build();
		Option releaseBranch = Option.builder("release").required(false).hasArg(true).longOpt("releaseBranchName").desc("Name of the branch which is release candidate").build();
		options.addOption(repoPath);
		options.addOption(masterBranch);
		options.addOption(releaseBranch);
		CommandLineParser parser = new DefaultParser();
	    try {
	        cmd = parser.parse( options, args);
	    }
	    catch( ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	        System.exit(1);
	    }
	}

	public InputParameters inputParameters() {
		InputParameters userInput = new InputParameters();
		userInput.repoPath(cmd.getOptionValue("repo"));
		if (cmd.hasOption("master")){
			userInput.masterBranch(cmd.getOptionValue("master"));
		}
		if (cmd.hasOption("release")){
			userInput.releaseBranch(cmd.getOptionValue("release"));
		}
		return userInput;
	}

}

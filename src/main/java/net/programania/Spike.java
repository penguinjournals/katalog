package net.programania;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;


public class Spike {
	public static void main(String[] args) throws RevisionSyntaxException, NoHeadException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, GitAPIException, IOException, URISyntaxException, ParseException{
		ArgumentParser argumentParser = new ArgumentParser(args);
		InputParameters input = argumentParser.inputParameters();
		GitCrawler gitCrawler = new GitCrawler(input.repoPath());
		List<GitChange> changelogBetweenMasterAndDevelop = gitCrawler.changelogBetweenTwoBranches(input.masterBranch(),input.releaseBranch());
		List<FullCommitInfo> fullCommitInfoChangelog = changelogWithJiraTaskDescription(changelogBetweenMasterAndDevelop);
        printChangelogToStdout(fullCommitInfoChangelog);
        System.exit(0);
	}

	private static List<FullCommitInfo> changelogWithJiraTaskDescription(List<GitChange> changelogBetweenMasterAndDevelop) throws URISyntaxException, IOException {
		JiraTicketResolver jiraTicketResolver = new JiraTicketResolver();
        List<FullCommitInfo> fullCommitInfoChangelog = new ArrayList<FullCommitInfo>();
        for (GitChange gitChange : changelogBetweenMasterAndDevelop) {
			for (String currentTicket: gitChange.foundTickets){
		        fullCommitInfoChangelog.add(new FullCommitInfo(gitChange.changeUniqueIdentifier(), currentTicket, jiraTicketResolver.jiraSummary(currentTicket)));
			}
		}
		return fullCommitInfoChangelog;
	}

	private static void printChangelogToStdout(List<FullCommitInfo> fullCommitInfoChangelog) {
		for (FullCommitInfo fullCommitInfo : fullCommitInfoChangelog) {
			System.out.println(fullCommitInfo.printableTicketNumber()+" "+fullCommitInfo.printableCommitID()+" "+fullCommitInfo.printableTicketSummary());
		}
	}
}

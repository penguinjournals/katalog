package net.programania;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class Spike {
	public static void main(String[] args) throws RevisionSyntaxException, NoHeadException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, GitAPIException, IOException, URISyntaxException{
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
		prop.load(input);
		String jiraURL = prop.getProperty("JIRA_URL");
		String jiraUser = prop.getProperty("JIRA_USER");
		String jiraPassword = prop.getProperty("JIRA_PASSWORD");
		GitCrawler gitCrawler = new GitCrawler("/Users/david/Desktop/cocotero");
		
		//GIT
		List<GitChange> changelogBetweenMasterAndDevelop = gitCrawler.changelogBetweenTwoBranches("master","develop");
		
		//JIRA
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI jiraServerUri = new URI(jiraURL);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUser, jiraPassword);
        List<FullCommitInfo> fullCommitInfoChangelog = new ArrayList<FullCommitInfo>();
        for (GitChange gitChange : changelogBetweenMasterAndDevelop) {
			for (String currentTicket: gitChange.foundTickets){
		        Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(currentTicket);
		        Issue issue = promiseIssue.claim();
		        fullCommitInfoChangelog.add(new FullCommitInfo(gitChange.changeUniqueIdentifier(), currentTicket, issue.getSummary()));
			}
		}
        printChangelogToStdout(fullCommitInfoChangelog);
        System.exit(0);
	}

	private static void printChangelogToStdout(List<FullCommitInfo> fullCommitInfoChangelog) {
		for (FullCommitInfo fullCommitInfo : fullCommitInfoChangelog) {
			System.out.println(fullCommitInfo.jiraTicketNumber+" "+fullCommitInfo.hash+" "+fullCommitInfo.jiraTicketSummary);
		}
	}
}

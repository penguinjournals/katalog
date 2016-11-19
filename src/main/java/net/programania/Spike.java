package net.programania;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
		List<GitChange> changelogBetweenMasterAndDevelop = gitCrawler.changelogBetweenTwoBranches("master","develop");
		final JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        final URI jiraServerUri = new URI("http://issue.intranet.enigmediacorp.com");
        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "david.gonzalez", "cocotero");
        final String issue = restClient.getIssueClient().getIssue("SC-10000").toString();
        System.out.println(issue);
	}
}

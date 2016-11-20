package net.programania;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class JiraTicketResolver {
	private String jiraURL;
	private String jiraUser;
	private String jiraPassword;
	private JiraRestClient restClient;

	public JiraTicketResolver() throws IOException, URISyntaxException {
		Properties prop = new Properties();
		InputStream input = new FileInputStream("config.properties");
		input = new FileInputStream("config.properties");
		prop.load(input);
		jiraURL = prop.getProperty("JIRA_URL");
		jiraUser = prop.getProperty("JIRA_USER");
		jiraPassword = prop.getProperty("JIRA_PASSWORD");
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI jiraServerUri = new URI(jiraURL);
        restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUser, jiraPassword);
	}
	
	public String jiraSummary(String ticketId){
        Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(ticketId);
        Issue issue = promiseIssue.claim();
        return issue.getSummary();
	}
}

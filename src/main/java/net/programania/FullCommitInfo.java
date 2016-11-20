package net.programania;

public class FullCommitInfo {
	String hash;
	String jiraTicketNumber;
	String jiraTicketSummary;
	
	public FullCommitInfo(String changeUniqueIdentifier, String currentTicket, String summary) {
		hash = changeUniqueIdentifier;
		jiraTicketNumber = currentTicket;
		jiraTicketSummary = summary;
	}
}

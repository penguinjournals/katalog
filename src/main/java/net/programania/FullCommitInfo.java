package net.programania;

public class FullCommitInfo {
	private String hash;
	private String jiraTicketNumber;
	private String jiraTicketSummary;
	
	public FullCommitInfo(String changeUniqueIdentifier, String currentTicket, String summary) {
		hash = changeUniqueIdentifier;
		jiraTicketNumber = currentTicket;
		jiraTicketSummary = summary;
	}
	
	public String printableTicketNumber() {
		return jiraTicketNumber;
	}
	
	public String printableTicketSummary() {
		return jiraTicketSummary;
	}
	
	public String printableCommitID() {
		return hash;
	}
}

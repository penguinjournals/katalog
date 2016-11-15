package net.programania;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GitChange{
	private String uniqueIdentifier;
	private String message;
	
	GitChange(String uniqueIdentifier, String message){
		this.uniqueIdentifier = uniqueIdentifier;
		this.message = message;
	}

	public String changeUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public String changeMessage() {
		return message;
	}
	
	public void extractJiraTickets() {
		String jiraTicketRegexp = "\\[SC-\\d{1,5}\\]";
		Pattern pattern = Pattern.compile(jiraTicketRegexp);
		Matcher jiraTickets = pattern.matcher(message);
		
		while (jiraTickets.find()) {	
			System.out.println(jiraTickets.group());	
		}
	}

}

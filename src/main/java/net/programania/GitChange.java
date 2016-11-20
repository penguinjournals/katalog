package net.programania;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GitChange{
	private String uniqueIdentifier;
	private String message;
	public List<String> foundTickets = new ArrayList<String>();
	
	GitChange(String uniqueIdentifier, String message){
		this.uniqueIdentifier = uniqueIdentifier;
		this.message = message;
		extractJiraTickets();
	}

	public String changeUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public String changeMessage() {
		return message;
	}
	
	private void extractJiraTickets() {
		String jiraTicketRegexp = "\\[SC-\\d{1,5}\\]";
		Pattern pattern = Pattern.compile(jiraTicketRegexp);
		Matcher jiraTickets = pattern.matcher(message);
		
		while (jiraTickets.find()) {	
			foundTickets.add(jiraTickets.group().replaceAll("\\[", "").replaceAll("\\]", ""));
		}
	}

	public void printJiraTickets() {
		// TODO Auto-generated method stub
		for (String ticket : foundTickets) {
			System.out.println(ticket);
		}
	}

}

package net.programania;

public class GitChange{
	private String uniqueIdentifier;
	private String message;
	
	public GitChange(String uniqueIdentifier, String message){
		this.uniqueIdentifier = uniqueIdentifier;
		this.message = message;
	}

	public String changeUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public String changeMessage() {
		return message;
	}

}

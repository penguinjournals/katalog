package net.programania;

public class gitChange{
	private String uniqueIdentifier;
	private String message;
	
	public gitChange(String uniqueIdentifier, String message){
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
